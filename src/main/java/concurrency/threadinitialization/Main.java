package concurrency.threadinitialization;

public final class Main {

    public static void main(String[] args) {
        // Creating thread by new class that extends Thread.
        CustomThread thread1 = new CustomThread();
        thread1.start();
        
        // Creating thread by passing class that implements Runnable to constructor of the Thread class.
        Thread thread2 = new Thread(new CustomRunnable());
        thread2.start();
        
        // Creating thread by anonymous Thread class.
        // In most cases it is better to use next Runnable example instead.
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                System.out.println("new Thread() { }");
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(3);
            }
        };
        thread3.start();
        
        // Creating thread by implementing Runnable in anonymous class.
        Thread thread4 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("new Thread(new Runnable() { })");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(4);
            }
        });
        thread4.start();
        
        // Since Java 8 you can create Thread with lambda expression.
        Thread thread5 = new Thread(() -> {
            System.out.println("new Thread(() -> { })");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println(5);
        });
        thread5.start();
        
        // Or using method reference.
        Thread thread6 = new Thread(Main::threadMethod);
        thread6.start();
    }
    
    private static void threadMethod() {
        System.out.println("new Thread(Main::threadMethod)");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println(6);
    }
}
