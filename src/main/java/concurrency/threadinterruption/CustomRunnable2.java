package concurrency.threadinterruption;

public class CustomRunnable2 implements Runnable {
    
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread %s started\n", name);
        
        int counter = 0;
        while (!Thread.interrupted()) {
            System.out.printf("Thread %s. counter = %d\n", name, counter);
            counter++;
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.printf("Thread %s interrupted\n", name);
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.printf("Thread %s finished\n", name);
    }

}
