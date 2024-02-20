package MultiThreading.basics.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample1 {
    public static void main(String[] args) {
        // Create a CompletableFuture representing an asynchronous task
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running computation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        // Chain a completion stage to the CompletableFuture
        CompletableFuture<String> resultFuture = future.thenApplyAsync(str -> {
            // Transform the result of the first task
            return str + " World";
        });

        // Specify an action to be performed when the result is available
        resultFuture.thenAccept(str -> {
            System.out.println("Result: " + str);
        });

        // Wait for the result
        try {
            resultFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
