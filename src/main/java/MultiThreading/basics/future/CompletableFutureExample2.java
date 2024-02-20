package MultiThreading.basics.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample2 {
    public static void main(String[] args) {
        // Create a CompletableFuture representing an asynchronous task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running computation
            try {
                Thread.sleep(2000); // Simulating a task that takes 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        // Specify a timeout duration
        long timeoutDuration = 2001; // Timeout duration in milliseconds

        // Add timeout to the CompletableFuture
        CompletableFuture<String> resultFuture = future.completeOnTimeout("Timeout occurred", timeoutDuration, TimeUnit.MILLISECONDS);

        // Specify an action to be performed when the result is available
        resultFuture.thenAccept(str -> {
            System.out.println("Result: " + str);
        });

        // Handle timeout exception
        resultFuture.exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return null; // Return null to handle the exception and prevent it from propagating further
        });

        // Wait for the result
        try {
            resultFuture.get(); // Wait for the result
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
