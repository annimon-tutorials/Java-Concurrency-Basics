package concurrency.sharedresources;

public class SynchronizedCounter {

    private static int counter = 0;

    public static synchronized int get() {
        return counter;
    }
    
    public static synchronized void increment() {
        counter++;
    }
}
