package MultiThreading.basics;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final int[] i = {0};
        executorService.execute(() -> System.out.println("Executing Thread "+ (i[0]++)));

    }

}
