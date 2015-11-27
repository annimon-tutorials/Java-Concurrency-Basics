package concurrency.sharedresources;

/**
 * Fix problem by adding synchronized block.
 */
public class CounterSolution {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = CounterSolution::printCounter;
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
        
        thread1.join();
        thread2.join();
        System.out.println("All threads finished. Counter = " + Counter.get());
    }
    
    private static void printCounter() {
        while (Counter.get() < 5000) {
            synchronized (Counter.class) {
                for (int i = 0; i < 10; i++) {
                    Counter.increment();
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // Here is another way to use synchronized block.
    // Lock object should be defined final.
    private static final Object lock = new Object();
    
    private static void printCounter2() {
        while (Counter.get() < 5000) {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    Counter.increment();
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
