package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FutureSample {
//    https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/Future.html

//    Sample Usage
//
//    interface ArchiveSearcher { String search(String target); }
//
//    class App {
//        ExecutorService executor = ...
//        ArchiveSearcher searcher = ...
//        void showSearch(String target) throws InterruptedException {
//            Callable<String> task = () -> searcher.search(target);
//            Future<String> future = executor.submit(task);
//            displayOtherThings(); // do other things while searching
//            try {
//                displayText(future.get()); // use future
//            } catch (ExecutionException ex) { cleanup(); return; }
//        }
//    }

//    Implementing Classes:
//    CompletableFuture, CountedCompleter, ForkJoinTask, FutureTask, RecursiveAction, RecursiveTask, SwingWorker

//    The FutureTask class is an implementation of Future that implements Runnable, and so may be executed by an Executor.
//    For example, the above construction with submit could be replaced by:
//
//    FutureTask<String> future = new FutureTask<>(task);
//    executor.execute(future);

//    ForkJoinTask is an abstract class which implements Future, and is capable of running a large number of tasks hosted by a small number of actual threads in ForkJoinPool.


//    user=> (instance? java.util.concurrent.Future (future (/ 1 0)))
//            true
//    user=> (instance? java.util.concurrent.CompletableFuture (future (/ 1 0)))
//            false

//    https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        // process async result
        CompletableFuture<String> future = completableFuture
                .thenApply(s -> s + " World");

        String result = future.get();
        System.out.println(result);

        // combining future
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"))
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        // Parallel Multiple Futures
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

//        CompletableFuture<Void> combinedFuture
//                = CompletableFuture.allOf(future1, future2, future3);
//
//        combinedFuture.get();

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(combined);

        // handling errors
        String name = null;
        completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        System.out.println(completableFuture.get());
    }
}
