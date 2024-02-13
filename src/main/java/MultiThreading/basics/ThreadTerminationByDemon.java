package MultiThreading.basics;

import java.math.BigInteger;

public class ThreadTerminationByDemon {
    public static void main(String[] args) {
    Thread thread =new BlockingTask(new BigInteger("200000"),new BigInteger("10000000"));
    thread.setDaemon(true);
    thread.start();


    }

    private static class BlockingTask extends Thread{
        private BigInteger base;
        private BigInteger power;
        
        BlockingTask(BigInteger base ,BigInteger power){
            this.base=base;
            this.power=power;
        }

        @Override
        public void run() {
            BigInteger pow = pow(base, power);
            System.out.println("base "+base +" power"+power+" result "+pow(base,power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result  =BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE)){
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread Interrupted");
                   return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }

}

