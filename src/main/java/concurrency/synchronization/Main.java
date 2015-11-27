package concurrency.synchronization;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public final class Main extends JFrame {
    
    private enum Mode {
        WITHOUT_THREADS,
        THREAD_FOR_EACH_FILE,
        THREAD_POOL_5_THREADS,
        THREAD_POOL_ACCORDING_TO_PROCESSORS_COUNT
    }

    public static void main(String[] args) {
        Main main = new Main();
//        main.addPaintPanel(new PaintPanelWithoutSynchronized(800, 500));
//        main.addPaintPanel(new PaintPanelWithSynchronized(800, 500));
        main.addPaintPanel(new PaintPanelSynchronizedList(800, 500));
//        main.addPaintPanel(new PaintPanelCopyOnWriteArrayList(800, 500));

        
//        main.start(Mode.WITHOUT_THREADS);
//        main.start(Mode.THREAD_FOR_EACH_FILE);
//        main.start(Mode.THREAD_POOL_5_THREADS);
        main.start(Mode.THREAD_POOL_ACCORDING_TO_PROCESSORS_COUNT);
    }
    
    private PaintPanel panel;
    
    public Main() {
        super("Triangles Demo");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void addPaintPanel(PaintPanel panel) {
        this.panel = panel;
        add(panel);
        pack();
        setVisible(true);
    }
    
    private void start(Mode mode) {
        long startTime = System.currentTimeMillis();
        File[] files = readFiles("dat");
        switch (mode) {
            case WITHOUT_THREADS:
                startWithoutThreads(files);
                break;
            case THREAD_FOR_EACH_FILE:
                startThreads(files);
                break;
            case THREAD_POOL_5_THREADS:
                startThreadPool(files, 5);
                break;
            case THREAD_POOL_ACCORDING_TO_PROCESSORS_COUNT:
                startThreadPool(files, Runtime.getRuntime().availableProcessors());
                break;
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("\nElapsed time: %d ms\n", elapsedTime);
    }
    
     private void startWithoutThreads(File[] files) {
        for (File file : files) {
            processFile(file);
        }
    }
     
    private void startThreads(File[] files) {
        List<Thread> threads = new ArrayList<>(files.length);
        for (File file : files) {
            Thread thread = new Thread(() -> processFile(file));
            threads.add(thread);
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void startThreadPool(File[] files, int maxThreads) {
        ExecutorService es = Executors.newFixedThreadPool(maxThreads);
        for (File file : files) {
            es.execute( () -> processFile(file) );
        }
        es.shutdown();
        try {
            es.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void processFile(File file) {
        System.out.printf("Process file %s in %s thread\n", file.getName(), Thread.currentThread().getName());
        try(FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                Triangle triangle = Triangle.read(dis);
                panel.addTriangle(triangle);
                Thread.sleep(1);
            }
        } catch (IOException | InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    
    private static File[] readFiles(String path) {
        File dir = new File(path);
        return dir.listFiles((d, name) -> name.toLowerCase().endsWith(".dat"));
    }
}
