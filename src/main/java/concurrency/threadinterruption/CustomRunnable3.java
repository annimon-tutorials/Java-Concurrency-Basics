package concurrency.threadinterruption;

public class CustomRunnable3 implements Runnable {
    
    private volatile boolean isRunning;
    
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s started\n", name);
        
        int counter = 0;
        try {
            while (isRunning) {
                System.out.printf("Thread %s. counter = %d\n", name, counter);
                counter++;

                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            System.out.printf("Thread %s interrupted\n", name);
            Thread.currentThread().interrupt();
        }
        
        System.out.printf("Thread %s finished\n", name);
    }

}
