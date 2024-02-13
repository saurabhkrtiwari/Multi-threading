package MultiThreading.basics;

public class ThreadCreation {
    public static void main(String[] args) {
        Thread thread1= new ExtendThread();
        Thread thread2 =new Thread(new RunnableThread());

        Runnable thread3 = () -> {
            try {

                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
            for(int i =0;i<10;i++) {
                System.out.println("Lambda Thread "+Thread.currentThread().getName());
            }
        };
        thread1.setName("thread1");
        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t,Throwable e){
                System.out.println("A Critical Error "+t.getName() +" error "+e.getMessage());
            }
        });
        thread2.setName("thread2");



        thread2.setPriority(Thread.MAX_PRIORITY);
        thread1.setPriority(Thread.MIN_PRIORITY);

        Thread thread = new Thread(thread3);
        thread.setName("thread3");
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.start();
        thread1.start();
        thread2.start();

    }

}

class ExtendThread extends Thread{

    @Override
    public void run() {

           throw new RuntimeException("Testing an exception in thread");

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
            System.out.println("Runnable thread "+Thread.currentThread().getName());
        }
    }
}


