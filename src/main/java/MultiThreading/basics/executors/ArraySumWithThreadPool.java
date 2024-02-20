package MultiThreading.basics.executors;

import java.util.concurrent.*;

public class ArraySumWithThreadPool {
    public static void main(String[] args) {
        // Sample array
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Number of threads to use
        int numThreads = 4;

        // Calculate chunk size
        int chunkSize = (int) Math.ceil((double) array.length / numThreads);

        // Create ThreadPoolExecutor with a fixed number of threads
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);

        // Future array to hold the results of each thread
        Future<Integer>[] futures = new Future[numThreads];

        // Submit tasks to ThreadPoolExecutor
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, array.length);
            futures[i] = executor.submit(new ArraySumTask(array, start, end));
        }

        // Sum up the results from all threads
        int totalSum = 0;
        for (Future<Integer> future : futures) {
            try {
                totalSum += future.get(1000L,TimeUnit.MILLISECONDS); // get() method blocks until the result is available

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();

            } catch (TimeoutException e) {
                System.out.println("TimeOut");
                System.out.println("Thread Interrupted");
                System.exit(0);
                throw new RuntimeException(e);
            }
        }

        // Shutdown ThreadPoolExecutor
        executor.shutdown();

        // Print the total sum
        System.out.println("Total sum of the array: " + totalSum);
    }

    // Task to calculate the sum of a portion of the array
    static class ArraySumTask implements Callable<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public ArraySumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() {
            int sum = 0;
            try {
                System.out.println("Thread is sleeping");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
                System.exit(0);
            }
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }
    }
}
