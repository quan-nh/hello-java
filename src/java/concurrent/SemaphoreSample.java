package concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreSample {
    // https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html
    // Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource.

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        semaphore.acquire();

        try {
            // do ops
        } finally {
            semaphore.release();
        }
    }
}
