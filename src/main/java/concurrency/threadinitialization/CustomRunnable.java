package concurrency.threadinitialization;

public class CustomRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("class CustomRunnable implements Runnable { }");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(2);
    }
}
