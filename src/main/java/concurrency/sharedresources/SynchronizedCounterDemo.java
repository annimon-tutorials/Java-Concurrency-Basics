package concurrency.sharedresources;

public class SynchronizedCounterDemo {
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(SynchronizedCounterDemo::printSynchronizedCounter);
        Thread thread2 = new Thread(SynchronizedCounterDemo::printSynchronizedCounter);

        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        System.out.println("All threads finished. Counter = " + SynchronizedCounter.get());
    }
    
    private static void printSynchronizedCounter() {
        while (SynchronizedCounter.get() < 5000) {
            for (int i = 0; i < 10; i++) {
                SynchronizedCounter.increment();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
