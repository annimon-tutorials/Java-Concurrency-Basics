package concurrency.sharedresources;

public class AtomicCounterDemo {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = AtomicCounterDemo::printAtomicCounter;
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        System.out.println("All threads finished. Counter = " + AtomicCounter.get());
    }
    
    private static void printAtomicCounter() {
        while (AtomicCounter.get() < 5000) {
            for (int i = 0; i < 10; i++) {
                AtomicCounter.increment();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
