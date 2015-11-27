package concurrency.sharedresources;

/**
 * Counter problem example.
 * Since threads using shared resource, the result can be different sometimes.
 */
public class CounterProblem {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = CounterProblem::printCounter;
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
            for (int i = 0; i < 10; i++) {
                Counter.increment();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
