package concurrency.sharedresources;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private static final AtomicInteger counter = new AtomicInteger();

    public static int get() {
        return counter.get();
    }
    
    public static void increment() {
        counter.incrementAndGet();
    }
}
