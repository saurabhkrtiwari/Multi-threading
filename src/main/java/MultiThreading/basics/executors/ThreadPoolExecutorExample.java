package MultiThreading.basics.executors;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        // Creating ThreadPoolExecutor using different constructors

        // 1. Using Core Pool Size, Max Pool Size, and Queue Capacity
        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(
                2,               // Core Pool Size
                4,               // Max Pool Size
                10,              // Keep Alive Time (10 seconds)
                TimeUnit.SECONDS, // Time Unit for Keep Alive Time
                new ArrayBlockingQueue<>(10) // Blocking Queue for holding tasks
        );

        // 2. Using Core Pool Size, Max Pool Size, Keep Alive Time, Time Unit,
        //    Blocking Queue, Thread Factory, and Rejected Execution Handler
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(
                2,               // Core Pool Size
                4,               // Max Pool Size
                10,              // Keep Alive Time (10 seconds)
                TimeUnit.SECONDS, // Time Unit for Keep Alive Time
                new ArrayBlockingQueue<>(10), // Blocking Queue for holding tasks
                Executors.defaultThreadFactory(), // Thread Factory for creating threads
                new ThreadPoolExecutor.AbortPolicy() // Rejected Execution Handler
        );

        // 3. Using Core Pool Size, Max Pool Size, Keep Alive Time, Time Unit,
        //    Blocking Queue, Thread Factory, Rejected Execution Handler, and Initializer
        ThreadPoolExecutor executor3 = new ThreadPoolExecutor(
                2,               // Core Pool Size
                4,               // Max Pool Size
                10,              // Keep Alive Time (10 seconds)
                TimeUnit.SECONDS, // Time Unit for Keep Alive Time
                new ArrayBlockingQueue<>(10), // Blocking Queue for holding tasks
                Executors.defaultThreadFactory(), // Thread Factory for creating threads
                new ThreadPoolExecutor.AbortPolicy() // Rejected Execution Handler
                 // Initializer
        );

        // Displaying the properties of each ThreadPoolExecutor
        displayProperties(executor1, "Executor 1");
        displayProperties(executor2, "Executor 2");
        displayProperties(executor3, "Executor 3");

        // Shutdown all executors
        executor1.shutdown();
        executor2.shutdown();
        executor3.shutdown();
    }

    // Method to display properties of ThreadPoolExecutor
    private static void displayProperties(ThreadPoolExecutor executor, String name) {
        System.out.println("[" + name + "]");
        System.out.println("Core Pool Size: " + executor.getCorePoolSize());
        System.out.println("Max Pool Size: " + executor.getMaximumPoolSize());
        System.out.println("Keep Alive Time: " + executor.getKeepAliveTime(TimeUnit.SECONDS) + " seconds");
        System.out.println("Queue Capacity: " + executor.getQueue().remainingCapacity());
        System.out.println("Rejected Execution Handler: " + executor.getRejectedExecutionHandler().getClass().getSimpleName());
        System.out.println();
    }
}
