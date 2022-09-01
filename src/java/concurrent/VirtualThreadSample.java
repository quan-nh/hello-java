package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

// java --enable-preview --source 19 VirtualThreadSample.java
public class VirtualThreadSample {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> System.out.println("Hello, world");

        // Platform thread
        Thread platformThread = new Thread(task);
        platformThread.start();

        // Virtual thread
        Thread virtualThread = Thread.startVirtualThread(task);
        Thread ofVirtualThread = Thread.ofVirtual().start(task);

        // Virtual thread created with a factory
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread virtualThreadFromAFactory = factory.newThread(task);
        virtualThreadFromAFactory.start();
        Thread.sleep(1000);

        //numberOfPlatformThreads(); // ~ 4063 & crash on macbook pro 6 core i7, 16GB ram
        //numberOfVirtualThreads(); // 3_955_152 & slowly run

        structuredConcurrency();
    }

    private static void numberOfPlatformThreads () {
        var counter = new AtomicInteger();
        while (true) {
            new Thread(() -> {
                int count = counter.incrementAndGet();
                System.out.println("Thread count = " + count);
                LockSupport.park();
            }).start();
        }
    }

    private static void numberOfVirtualThreads () {
        var counter = new AtomicInteger();
        while (true) {
            Thread.startVirtualThread(() -> {
                int count = counter.incrementAndGet();
                System.out.println("Thread count = " + count);
                LockSupport.park();
            });
        }
    }

    private static void structuredConcurrency () {
        // Structured concurrency binds the lifetime of threads to the code block which created them.
        // This binding is implemented by making the ExecutorService Autocloseable, making it possible to use ExecutorServices in a try-with-resources.
        // When all tasks are submitted, the current thread will wait till the tasks are finished and the close method of the ExecutorService is done.
        System.out.println("---------");
        try (ExecutorService e = Executors.newVirtualThreadPerTaskExecutor()) {
            e.execute(() -> System.out.println("1"));
            e.execute(() -> System.out.println("2"));
        }
        System.out.println("---------");
    }
}
