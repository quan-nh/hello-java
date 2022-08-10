package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// java --enable-preview --source 19 VirtualThreadSample.java
public class VirtualThreadSample {
    public static void main(String[] args) throws InterruptedException {
//        Runnable task = () -> System.out.println("Hello, world");

        // Platform thread
//        (new Thread(task)).start();
//        Thread platformThread = new Thread(task);
//        platformThread.start();

        // Virtual thread
//        Thread virtualThread = Thread.startVirtualThread(task);
//        Thread ofVirtualThread = Thread.ofVirtual().start(task);
//        Thread.sleep(1000);

        // Structured concurrency
        System.out.println("---------");
//        try (ExecutorService e = Executors.newVirtualThreadPerTaskExecutor()) {
//            e.execute(() -> System.out.println("1"));
//            e.execute(() -> System.out.println("2"));
//        }
        System.out.println("---------");
    }
}
