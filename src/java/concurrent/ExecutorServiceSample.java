package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSample {

    public static void main(String[] args) {
        // ExecutorService automatically provides a pool of threads and an API for assigning tasks to it.
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // execute Runnable tasks
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println("runnableTask " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.execute(runnableTask);

        // execute Callable tasks & get result
        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "callableTask's execution";
        };

        Future<String> future = executorService.submit(callableTask);
        try {
            String result = future.get(); // block until result is available
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        // In general, the ExecutorService will not be automatically destroyed when there is no task to process.
        // It will stay alive and wait for new work to do.

        // To properly shut down an ExecutorService, we have the shutdown() and shutdownNow() APIs.

        executorService.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}


