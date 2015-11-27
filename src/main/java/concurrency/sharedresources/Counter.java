package concurrency.sharedresources;

public class Counter {

    private static int counter = 0;

    public static int get() {
        return counter;
    }
    
    public static void increment() {
        counter++;
    }
}
