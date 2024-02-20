package MultiThreading.basics.future;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> piFuture = fetchFromApi(client, "http://numbersapi.com/pi");
        CompletableFuture<String> eFuture = fetchFromApi(client, "http://numbersapi.com/e");
        CompletableFuture<String> phiFuture = fetchFromApi(client, "http://numbersapi.com/phi");
        CompletableFuture<String> sqrt2Future = fetchFromApi(client, "http://numbersapi.com/sqrt2");

        // Wait for all futures to complete
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(piFuture, eFuture, phiFuture, sqrt2Future);

        allFutures.thenRun(() -> {
            try {
                System.out.println("PI: " + piFuture.get());
                System.out.println("E: " + eFuture.get());
                System.out.println("PHI: " + phiFuture.get());
                System.out.println("Square Root of 2: " + sqrt2Future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetchFromApi(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
