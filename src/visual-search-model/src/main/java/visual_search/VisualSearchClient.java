package visual_search;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.List;

public class VisualSearchClient {

    public static void main(String[] args) throws Exception {
        String target = "localhost:50051";

        // Create a communication channel to the server
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext() // IMPORTANT: Disable SSL/TLS for local development
                .build();

        try {
            // Create a stub (client-side proxy)
            VisualSearchGrpc.VisualSearchServiceBlockingStub stub = VisualSearchGrpc.newBlockingStub(channel);

            // Create a request
            VisualSearchOuterClass.VisualSearchRequest request = VisualSearchOuterClass.VisualSearchRequest.newBuilder()
                    .setImageUrl("https://cdn.britannica.com/68/195168-050-BBAE019A/football.jpg")
                    .setText("This is a test query for football.")
                    .build();

            // Call the remote method and get the response
            VisualSearchOuterClass.VisualSearchResponse response = stub.search(request);

            // Print Results
            System.out.println("Similar Images: ");
            List<String> similarImageUrls = response.getSimilarImageUrlsList();
            for (int i=0; i< similarImageUrls.size(); i++){
                System.out.println(String.format("%d. %s", i+1, similarImageUrls.get(i)));
            }
            System.out.println("Similar Text Ids: " + response.getSimilarTextIdsList());

        } finally {
            // Shutdown the channel
            channel.shutdownNow();
        }
    }
}