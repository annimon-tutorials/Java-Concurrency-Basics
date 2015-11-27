package concurrency.threadinterruption;

public final class Main {

    public static void main(String[] args) throws InterruptedException {
        CustomRunnable1 runnable = new CustomRunnable1();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread.sleep(500);
        thread1.interrupt(); // interrupt() does not finishes thread in this case
        Thread.sleep(50);
        System.out.printf("Thread %s isAlive: %b\n", thread1.getName(), thread1.isAlive());
        runnable.stop();
        Thread.sleep(50);
        System.out.printf("Thread %s isAlive: %b\n", thread1.getName(), thread1.isAlive());
        
        System.out.println("---------");
        
        Thread thread2 = new Thread(new CustomRunnable2(), "second");
        thread2.start();
        Thread.sleep(500);
        thread2.interrupt();
        Thread.sleep(50);
        System.out.printf("Thread %s isAlive: %b\n", thread2.getName(), thread2.isAlive());
        
        System.out.println("---------");
        
        CustomRunnable3 runnable3 = new CustomRunnable3();
        Thread thread3 = new Thread(runnable3);
        thread3.start();
        Thread.sleep(500);
        // now both methods works fine
        thread3.interrupt();
        //runnable3.stop();
        Thread.sleep(50);
        System.out.printf("Thread %s isAlive: %b\n", thread3.getName(), thread3.isAlive());
    }
   
}
