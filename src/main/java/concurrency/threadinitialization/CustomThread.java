package concurrency.threadinitialization;

public class CustomThread extends Thread {

    @Override
    public void run() {
        System.out.println("class CustomThread extends Thread { }");
        try {
            sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(1);
    }
}
