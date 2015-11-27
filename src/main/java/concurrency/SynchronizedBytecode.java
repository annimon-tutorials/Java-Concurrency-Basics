package concurrency;

/**
 * Difference between synchronized method and synchronized block in bytecode.
 */
public class SynchronizedBytecode {
    
    private static int counter;

    public static int getCounter() {
        return counter;
    }
    
    public static synchronized int getCounterSynchronizedMethod() {
        return counter;
    }
    
    public static int getCounterSynchronizedBlock() {
        synchronized (SynchronizedBytecode.class) {
            return counter;
        }
        // monitorenter (SynchronizedBytecode.class)
        // try {
        //     return counter
        // } finally {
        //     monitorexit
        // }
    }
}
