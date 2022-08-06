package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceSample {
    public static void main(String[] args) {
        // The ScheduledExecutorService runs tasks after some predefined delay and/or periodically.
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();

//        Future<String> resultFuture =
//                executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
//
//        Future<String> resultFuture = executorService
//                .scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
//
//        executorService.scheduleWithFixedDelay(task, 100, 150, TimeUnit.MILLISECONDS);

    }
}
