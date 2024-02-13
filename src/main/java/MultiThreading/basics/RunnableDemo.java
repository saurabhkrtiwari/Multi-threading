package MultiThreading.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunnableDemo {

    public static final int MAX_PASSWORD=9999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(1111,MAX_PASSWORD));
        List<Thread> threads = new ArrayList<>();
        AscendingHackerThread ascendingHackerThread = new AscendingHackerThread(vault);
        DecendingHackerThread decendingHackerThread = new DecendingHackerThread(vault);
        PoliceThread policeThread = new PoliceThread();

        threads.add(ascendingHackerThread);
        threads.add(decendingHackerThread);
        threads.add(policeThread);
        threads.forEach(Thread::start);


    }

    private static class Vault{
        private int password;
        public  Vault(int password){
            this.password=password;
        }
        public boolean isCorrectPassword(int guess){
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault){
            this.vault=vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Staring "+this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread{

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        public void run(){
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if(vault.isCorrectPassword(i)){
                    System.out.println("Vault hacked "+this.getName() + " password is "+i);
                    System.exit(0);
                }
            }
        }
    }

    private static class DecendingHackerThread extends HackerThread{

        public DecendingHackerThread(Vault vault) {
            super(vault);
        }

        public void run(){
            for (int i = MAX_PASSWORD; i >0; i--){
                if(vault.isCorrectPassword(i)){
                    System.out.println("Vault hacked "+this.getName() + " password is "+i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread{
        @Override
        public void run() {
            for (int i = 10; i >0 ; i--) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Waiting for police "+i);
            }

            System.out.println("Police arrived "+ this.getName());
            System.exit(0);
        }

    }
}
