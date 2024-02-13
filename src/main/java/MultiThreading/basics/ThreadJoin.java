package MultiThreading.basics;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoin {
    /**
     * @param args
     */
    public static void main(String[] args) {

        List<Long> inputNubers = Arrays.asList(0L,3993987L,2L,2342L,76L,987L,21L,321L,2L,2342L,76L,27L,21L,321L);

        List<FactorialThread> factorialThreads = new ArrayList<>();
        inputNubers.forEach(i->factorialThreads.add(new FactorialThread(i)));

        factorialThreads.forEach(FactorialThread::start);
        // factorialThreads.forEach(arg0 -> {
        //     try {
        //         arg0.join();
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // });

        factorialThreads.forEach(arg0 -> {
            try {
                arg0.join(1000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        for (int j = 0; j < inputNubers.size(); j++) {
            
        
            FactorialThread factorialThread = factorialThreads.get(j);
            if(factorialThread.isFinished()){
                System.out.println("for input number "+inputNubers.get(j)+" Result is "+factorialThread.getResult());
            }else{
                System.out.println("The calculation for "+inputNubers.get(j)+" is in progess ");
                factorialThread.interrupt();
            }
            
        }

    }

    private static class FactorialThread extends Thread{
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

       public FactorialThread(long inputNumber){
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result =factorial(inputNumber);
            isFinished = true;
        }

        public BigInteger factorial(long inputNumber){
            BigInteger result = BigInteger.ONE;
            for (long i = inputNumber; i >0; i--) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread Interrupted");
                   return BigInteger.ZERO;
                }
                result = result.multiply(new BigInteger(Long.toString(i)));
            }
            return result;
        }

        public boolean isFinished(){
            return isFinished;
        }

        public BigInteger getResult(){
            return result;
        }

    }
}
