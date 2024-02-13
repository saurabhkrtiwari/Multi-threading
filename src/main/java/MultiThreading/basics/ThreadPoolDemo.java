package MultiThreading.basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executer =Executors.newFixedThreadPool(5);
        for (int i = 0; i < 25; i++) {
            Runnable worker = new Worker();
            executer.execute(worker);
        }
        executer.shutdown();

        while (!executer.isTerminated()) {}

        System.out.println("Finished all threads");
            
        }
    }

   
    


 class Worker implements Runnable{

    @Override
    public void run() {
      System.out.println("In worker thread "+Thread.currentThread().getName());
      processMessage();
      System.out.println("Existing from worker "+Thread.currentThread().getName());
    }

    private void processMessage() {

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}