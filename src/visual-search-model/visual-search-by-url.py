

import IPython
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
from scipy.sparse import csr_matrix
from sklearn.neighbors import NearestNeighbors
import io
import random
from sentence_transformers import SentenceTransformer, util


# Step 1: Extract Image Embeddings and Cache Locally using ResNet
EMBEDDING_CACHE = "embeddings_cache.pkl"
embeddings_cache = {}

transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
])
transform

# Load a pre-trained ResNet model
model = models.resnet50(weights=models.ResNet50_Weights.IMAGENET1K_V1)
model = torch.nn.Sequential(*(list(model.children())[:-1]))  # Remove the classification layer
model.eval()

# Load cached embeddings if available
if os.path.exists(EMBEDDING_CACHE) and os.path.getsize(EMBEDDING_CACHE) > 0:
    with open(EMBEDDING_CACHE, "rb") as f:
        embeddings_cache = pickle.load(f)
    print("Embeddings cache loaded successfully.")
else:
    print("No cache!!!")

def extract_embedding_from_url(image_url):
    # Check cache first
    if image_url in embeddings_cache:
        return embeddings_cache[image_url]
    
    try:
        # Use requests.get() without stream to ensure full content is downloaded
        response = requests.get(image_url, timeout=10)
        response.raise_for_status()  # Raise an exception for bad HTTP responses
        
        # Use BytesIO to create a file-like object
        image_data = io.BytesIO(response.content)
        
        # Open image with explicit error handlinga
        try:
            image = Image.open(image_data).convert("RGB")
        except UnidentifiedImageError:
            print(f"Could not identify image from {image_url}")
            return None
        
        # Apply transformations
        image = transform(image).unsqueeze(0)  # Add batch dimension
        
        with torch.no_grad():
            embedding = model(image).squeeze().numpy()
        
        # Cache the embedding
        embeddings_cache[image_url] = embedding
        return embedding
    
    except requests.RequestException as e:
        print(f"Error fetching image from {image_url}: {e}")
        return None
    except Exception as e:
        print(f"Unexpected error processing {image_url}: {e}")
        return None

# Save the cache after each run
def save_embeddings_cache():
    with open(EMBEDDING_CACHE, "wb") as f:
        pickle.dump(embeddings_cache, f)

def extract_embedding_multithread(image_links, max_thread = 10):
    with ThreadPoolExecutor(max_workers=max_thread) as executor:
        results = list(tqdm(
            executor.map(extract_embedding_from_url, image_links),
            total = len(image_links),
            desc = "Extracting embeddings"
        ))
    total_images = len(image_links)
    successful_embeddings = sum(1 for result in results if result is not None)
    failed_count = total_images - successful_embeddings
    print(f"Extraction complete. "
                f"Total images: {total_images}, "
                f"Successful: {successful_embeddings}, "
                f"Failed: {failed_count}")

def find_similar_cosine(query_embedding, embeddings, top_k=5):
    similarities = cosine_similarity([query_embedding], embeddings)[0]
    top_indices = similarities.argsort()[-top_k:][::-1]
    return top_indices, similarities

# Step 3: Find Similar Images from Cache
def find_similar_images(query_image_url, top_k=20):
    print("Extracting query image embedding...")
    query_embedding = extract_embedding_from_url(query_image_url)
    if query_embedding is None:
        return []
    
    embeddings = []
    valid_image_urls = []

    print("Loading embeddings from cache...")
    for url, embedding in embeddings_cache.items():
        embeddings.append(embedding)
        valid_image_urls.append(url)

    if not embeddings:
        print("No embeddings available in the cache.")
        return []

    embeddings = np.vstack(embeddings)
    query_embedding = np.array(query_embedding)

    top_indices, similarities = find_similar_cosine(query_embedding, embeddings, top_k)

    # save_embeddings_cache()  # Save updated cache

    # print("Top similar images:")
    
    # for i, index in enumerate(top_indices, 1):
    #     print(f"{index}.{similarities[index]}: {valid_image_urls[index]}")
    return [valid_image_urls[i] for i in top_indices]

# query is the image url of the searched image
query = "https://cdn.britannica.com/68/195168-050-BBAE019A/football.jpg"
# similar_images is the list of 20 closest matched image links from database
similar_images = find_similar_images(query_image_url=query)
for img_url in similar_images:
    if similar_images.index(img_url) == 0:
        continue
    print(f"{similar_images.index(img_url)}. {img_url}")
