package MultiThreading.basics;

public class ThreadCreation {
    public static void main(String[] args) {
        Thread thread1= new ExtendThread();
        Thread thread2 =new Thread(new RunnableThread());

        Runnable thread3 = new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(int i =0;i<10;i++) {
                    System.out.println("lambda thread "+i);
                }
            }
        };

        thread1.start();
        thread2.start();
        new Thread(thread3).start();

    }

}

class ExtendThread extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i =0;i<10;i++) {
            System.out.println("Extending thread "+i);
        }
    }
}

class RunnableThread implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i =0;i<10;i++) {
            System.out.println("Runnable thread "+i);
        }
    }
}


