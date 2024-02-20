package MultiThreading.basics.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
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
        long timeoutDuration = 1500; // Timeout duration in milliseconds

        // CompletableFuture to handle the timeout
        CompletableFuture<String> timeoutFuture = new CompletableFuture<>();
        CompletableFuture<Void> delayedFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(timeoutDuration);
                timeoutFuture.complete("Timeout occurred");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Combine the results of the original CompletableFuture and the timeout CompletableFuture
        CompletableFuture<String> resultFuture = future.thenCombine(timeoutFuture, (result, timeoutResult) -> {
            if (timeoutFuture.isDone()) {
                return timeoutResult;
            } else {
                return result;
            }
        });

        // Specify an action to be performed when the result is available
        resultFuture.thenAccept(str -> {
            System.out.println("Result: " + str);
        });

        // Wait for the result
        try {
            resultFuture.get(); // Wait for the result
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
