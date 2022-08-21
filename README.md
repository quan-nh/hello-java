# hello-java

- Concurrent
  + [Thread](src/java/concurrent/ThreadSample.java)
  + [Runnable vs. Callable](src/java/concurrent/RunnableCallable.java)
  + [Future & CompletableFuture](src/java/concurrent/FutureSample.java)
  + [ExecutorService](src/java/concurrent/ExecutorServiceSample.java)
  + [ForkJoinPool](src/java/concurrent/ForkJoinPoolSample.java)
  + [BlockingQueue](src/java/concurrent/BlockingQueueSample.java)

- ExecutorService vs Fork/Join
  + ExecutorService gives the developer the ability to control the number of generated threads and the granularity of tasks that should be run by separate threads. The best use case for ExecutorService is the processing of independent tasks, such as transactions or requests according to the scheme “one thread for one task.
  + In contrast, fork/join was designed to speed up work that can be broken into smaller pieces recursively.