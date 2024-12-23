import asyncio
import grpc
from concurrent import futures
import visual_search_pb2 as pb2
import visual_search_pb2_grpc as pb2_grpc
import os
import pickle
import requests
from concurrent.futures import ThreadPoolExecutor
from tqdm import tqdm
import torch
from torchvision import models, transforms
from PIL import Image
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.preprocessing import normalize
import pandas as pd
import io
from sentence_transformers import SentenceTransformer, util

# --- Data Loading and Preprocessing ---
# Define path to the dataset CSV file
dataset_path = "dataset.csv"
# Load the dataset into a pandas DataFrame
dataset = pd.read_csv(dataset_path)
# Create a combined text column for text-based searches
dataset['combined_text'] = dataset['category_1'] + " " + dataset['category_2'] + " " + dataset['category_3'] + " " + dataset['title']

# Initialize lists to store valid image indices and links
valid_image_indices = []
valid_image_links = []

# Filter image links in dataset:
for idx, url in enumerate(dataset['image_links']):
    # Only store image links that are strings and start with "http"
    if isinstance(url, str) and url.startswith('http'):
        valid_image_indices.append(idx)
        valid_image_links.append(url)

# --- Image Embedding Setup ---
# Define the path for the image embedding cache file
EMBEDDING_CACHE = "embeddings_cache.pkl"
# Initialize a dictionary for storing image embeddings
embeddings_cache = {}

# Define image transformations for ResNet50 input
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
])

# Load a pre-trained ResNet50 model, removing the classification layer
model = models.resnet50(weights=models.ResNet50_Weights.IMAGENET1K_V1)
model = torch.nn.Sequential(*(list(model.children())[:-1]))
model.eval()


# Function to extract image embedding from a URL
def extract_embedding_from_url(image_url):
    # Check cache first
    if image_url in embeddings_cache:
        return embeddings_cache[image_url]

    try:
        # Download image from URL using requests
        response = requests.get(image_url, timeout=10)
        response.raise_for_status()  # Raise an exception for bad HTTP responses

        # Use BytesIO to create a file-like object
        image_data = io.BytesIO(response.content)

        # Open image with explicit error handling
        try:
            image = Image.open(image_data).convert("RGB")
        except Exception as e:
            print(f"extract_embedding_from_url: Could not identify image from {image_url}, error: {e}")
            return None

        # Apply transformations
        image = transform(image).unsqueeze(0)  # Add batch dimension

        with torch.no_grad():
            embedding = model(image).squeeze().numpy()

        # Cache the embedding
        embeddings_cache[image_url] = embedding
        # print(f"extract_embedding_from_url: Successfully extracted embedding for: {image_url} , type {type(embedding)}")
        return embedding

    except requests.RequestException as e:
        print(f"extract_embedding_from_url: Error fetching image from {image_url}: {e}")
        return None
    except Exception as e:
        print(f"extract_embedding_from_url: Unexpected error processing {image_url}: {e}")
        return None

# Function to save the image embeddings cache
def save_embeddings_cache():
    with open(EMBEDDING_CACHE, "wb") as f:
        pickle.dump(embeddings_cache, f)

# Function to extract embeddings using multithreading
def extract_embedding_multithread(image_links, max_thread=10):
    with ThreadPoolExecutor(max_workers=max_thread) as executor:
        results = list(tqdm(
            executor.map(extract_embedding_from_url, image_links),
            total=len(image_links),
            desc="Extracting embeddings"
        ))
    total_images = len(image_links)
    successful_embeddings = sum(1 for result in results if result is not None)
    failed_count = total_images - successful_embeddings
    print(f"Extraction complete. "
          f"Total images: {total_images}, "
          f"Successful: {successful_embeddings}, "
          f"Failed: {failed_count}")

# Function to calculate cosine similarity and get top indices
def find_similar_cosine(query_embedding, embeddings, top_k=5):
    if not isinstance(query_embedding, np.ndarray) or query_embedding.ndim != 1:
        print(f"find_similar_cosine: Invalid query_embedding type or dimension: {type(query_embedding)}, shape {getattr(query_embedding, 'shape', None)}")
        return [], []

    if not isinstance(embeddings, np.ndarray) or embeddings.ndim != 2:
        print(f"find_similar_cosine: Invalid embeddings type or dimension: {type(embeddings)}, shape {getattr(embeddings, 'shape', None)}")
        return [], []

    similarities = cosine_similarity([query_embedding], embeddings)[0]
    top_indices = similarities.argsort()[-top_k:][::-1]
    if not isinstance(top_indices,np.ndarray):
        print(f"find_similar_cosine: Invalid type returned for top_indices {type(top_indices)}")
        return [],[]
    if top_indices.ndim !=1:
        print(f"find_similar_cosine: Invalid dimension for top_indices {top_indices.ndim}")
        return [], []
    return top_indices, similarities

# Function to find similar images using cosine similarity
def find_similar_images(query_image_url, top_k=20):
    print("Extracting query image embedding...")
    query_embedding = extract_embedding_from_url(query_image_url)
    if query_embedding is None:
        print("find_similar_images: Could not extract embedding for query image.")
        return []

    embeddings = []
    valid_image_urls = []

    print("Loading embeddings from cache...")
    for url, embedding in embeddings_cache.items():
        embeddings.append(embedding)
        valid_image_urls.append(url)

    if not embeddings:
        print("find_similar_images: No embeddings available in the cache.")
        return []

    embeddings = np.vstack(embeddings)
    query_embedding = np.array(query_embedding)

    top_indices, similarities = find_similar_cosine(query_embedding, embeddings, top_k)
    if not isinstance(top_indices, np.ndarray):
        print(f"find_similar_images: Invalid type returned for top_indices {type(top_indices)}")
        return []
    return [valid_image_urls[i] for i in top_indices]

# Load cached embeddings if available, setting a flag if cache is loaded
embeddings_cache_loaded = False
if os.path.exists(EMBEDDING_CACHE) and os.path.getsize(EMBEDDING_CACHE) > 0:
    with open(EMBEDDING_CACHE, "rb") as f:
        embeddings_cache = pickle.load(f)
    print("Embeddings cache loaded successfully.")
    embeddings_cache_loaded = True
else:
    print("No image embeddings cache, creating new cache.")
    # If no cache exists, extract and save embeddings
    extract_embedding_multithread(valid_image_links, max_thread=10)
    save_embeddings_cache()
    embeddings_cache_loaded = True


# --- Text Embedding Setup ---
# Initialize text model and cache
text_model = SentenceTransformer('all-MiniLM-L6-v2')
TEXT_EMBEDDINGS_CACHE = "text_embeddings_cache.pkl"
text_embeddings_cache = {}


# Function to generate embeddings for a single text entry
def generate_text_embeddings(data_tuple):
    """Generate embeddings for a single text entry"""
    idx, text = data_tuple
    if not isinstance(text, str) or not text.strip():
        print(f"generate_text_embeddings: Invalid text at index {idx}")
        return idx, None

    if idx in text_embeddings_cache:
        return idx, text_embeddings_cache[idx]

    try:
        embedding = text_model.encode(text)
        text_embeddings_cache[idx] = embedding
        return idx, embedding
    except Exception as e:
        print(f"Error processing text at index {idx}: {e}")
        return idx, None

# Function to save the text embeddings cache
def save_text_embedding_cache():
    """Save embeddings cache to disk"""
    with open(TEXT_EMBEDDINGS_CACHE, "wb") as f:
        pickle.dump(text_embeddings_cache, f)

# Function to extract text embeddings using multithreading
def extract_text_embeddings_multithread(dataset, text_column='combined_text', batch_size=3000, max_thread=10):
    data_tuples = list(enumerate(dataset[text_column]))

    with ThreadPoolExecutor(max_workers=max_thread) as executor:
        results = list(tqdm(
            executor.map(generate_text_embeddings, data_tuples),
            total=len(data_tuples),
            desc="Extracting text embeddings"
        ))

    total_texts = len(data_tuples)
    successful_embeddings = sum(1 for _, emb in results if emb is not None)
    failed_count = total_texts - successful_embeddings

    print(f"Extraction complete. "
          f"Total rows: {total_texts}, "
          f"Successful: {successful_embeddings}, "
          f"Failed: {failed_count}")

    save_text_embedding_cache()
    return 0


# Load existing text cache if available, setting a flag if cache is loaded
text_embeddings_cache_loaded = False
if os.path.exists(TEXT_EMBEDDINGS_CACHE):
    try:
        with open(TEXT_EMBEDDINGS_CACHE, 'rb') as f:
            text_embeddings_cache = pickle.load(f)
        print("Text embeddings cache loaded successfully.")
        text_embeddings_cache_loaded = True
    except Exception as e:
        print(f"No text embeddings cache, creating new cache Error: {e}")
else:
    print("No text embeddings cache, creating new cache.")
    extract_text_embeddings_multithread(dataset, max_thread=10)
    save_text_embedding_cache()
    text_embeddings_cache_loaded = True


# Function to find similar texts using cosine similarity
def find_similar_texts(query_text, dataset, top_k):
    print("Extracting query text embedding...")
    query_embedding = text_model.encode(query_text)
    if query_embedding is None:
        print("find_similar_texts: Could not encode query text.")
        return []

    if not dataset:
        print("find_similar_texts: Empty dataset provided.")
        return []

    print("Loading embeddings from cache...")
    embeddings = []

    for idx in dataset:
        if isinstance(idx, int) and idx in text_embeddings_cache:
            embeddings.append(text_embeddings_cache[idx])
        else:
            print(f"find_similar_texts: Invalid index {idx} or no embedding found in cache.")

    if not embeddings:
        print("find_similar_texts: No embeddings available in the cache.")
        return []

    embeddings = np.vstack(embeddings)
    query_embedding = np.array(query_embedding)

    top_indices, similarities = find_similar_cosine(query_embedding, embeddings, top_k)
    if not isinstance(top_indices, np.ndarray):
        print(f"find_similar_texts: Invalid type returned by find_similar_cosine: {type(top_indices)}")
        return []
    return top_indices.tolist()

# Function for recommendation, combines both image and text similarities
def recommendation_system(query_text, query_image_url):
    print("Starting recommendation_system...")
    top_40_indices = find_similar_images(query_image_url=query_image_url, top_k=40)
    if not top_40_indices:
        print("recommendation_system: top_40_indices is empty, returning empty list.")
        return []

    #Get original indices based on similar urls:
    valid_image_url_map = {valid_image_links[idx]:idx for idx in range(len(valid_image_links))}
    top_40_original_indices = [valid_image_url_map[img] for img in top_40_indices if img in valid_image_url_map]

    top_5_closest_item = find_similar_texts(query_text=query_text, dataset=top_40_original_indices, top_k=10)

    if not isinstance(top_5_closest_item, list):
        print(f"recommendation_system: Invalid type returned by find_similar_texts, type: {type(top_5_closest_item)}")
        return []

    print(f"recommendation_system: top_5_closest_item: {top_5_closest_item}")
    return top_5_closest_item

# --- gRPC Service Implementation ---
class VisualSearchService(pb2_grpc.VisualSearchServiceServicer):
    async def Search(self, request, context):
        print("VisualSearchService.Search: Received search request")
        similar_images = find_similar_images(query_image_url=request.image_url)

        # Map image urls to indices in the original dataset, based on valid image urls from the cache

        valid_image_indices = []
        if similar_images:
            valid_image_url_map = {valid_image_links[idx]:idx for idx, url in enumerate(valid_image_links) if url in similar_images}
            valid_image_indices = [valid_image_url_map[img] for img in similar_images if img in valid_image_url_map]

        print(f"VisualSearchService.Search: valid_image_indices: {valid_image_indices}")
        similar_text_ids = []
        if len(valid_image_indices) > 0:
            similar_text_ids = recommendation_system(query_text=request.text, query_image_url=request.image_url)
            if not isinstance(similar_text_ids,list):
                print(f"VisualSearchService.Search: recommendation_system returned an invalid value: {similar_text_ids}")
                return pb2.VisualSearchResponse(similar_image_urls=similar_images, similar_text_ids=[])

                #Type validation before indexing
            similar_text_ids_int = []
            for idx in similar_text_ids:
                if not isinstance(idx,int):
                    print(f"VisualSearchService.Search: Invalid type {type(idx)} in similar_text_ids: {similar_text_ids}")
                    return pb2.VisualSearchResponse(similar_image_urls=similar_images, similar_text_ids=[])
                similar_text_ids_int.append(idx)
            similar_text_ids = similar_text_ids_int
        else:
            print("VisualSearchService.Search: No valid images, returning empty text ids.")

        if similar_images is None:
            print("VisualSearchService.Search: similar_images is None")
            similar_images = []

        if similar_text_ids is None:
            print("VisualSearchService.Search: similar_text_ids is None")
            similar_text_ids = []

        print(f"VisualSearchService.Search:  returning similar images: {similar_images}, similar text ids: {similar_text_ids}")
        return pb2.VisualSearchResponse(similar_image_urls=similar_images, similar_text_ids=similar_text_ids)

# --- gRPC Server Setup ---
async def serve():
    server = grpc.aio.server()
    pb2_grpc.add_VisualSearchServiceServicer_to_server(VisualSearchService(), server)
    server.add_insecure_port('[::]:50051')
    await server.start()
    print("Server started, listening on port 50051")
    await server.wait_for_termination()

# --- Main Execution Block ---
if __name__ == '__main__':
    # if not embeddings_cache_loaded:
    extract_embedding_multithread(valid_image_links, max_thread=10)
    # if not text_embeddings_cache_loaded:
    extract_text_embeddings_multithread(dataset, max_thread=10)
    asyncio.run(serve())