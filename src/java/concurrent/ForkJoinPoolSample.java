package concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolSample {
    // https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html
    // https://puredanger.github.io/tech.puredanger.com/2011/01/04/forkjoin-clojure/

    // The Executor interface decouples task submission from task execution.
    // In the normal java.util.concurrent executors, there is a single submission queue and all workers in a thread pool pull from that execution queue.

    // The problem with this approach is that as you increase the number of threads,
    // the queue becomes a source of contention (a bottleneck) as everyone synchronizes when pulling work from the queue.

    // In fork/join, every thread has its own queue, which is really a dequeue (a double-ended queue).
    // Each thread pulls only from the head of its own queue so there is no contention there.

    // The problem then becomes how to keep all the threads busy when they’re not playing from a single point of control.
    // This is done by allowing threads to steal work from the *back* of another thread’s queue (again, avoiding contention at the head of the queue).
    // This allows threads to “auto-balance” the work load.
    

    // The center of the fork/join framework is the ForkJoinPool class, an extension of the AbstractExecutorService class.
    // ForkJoinPool implements the core work-stealing algorithm and can execute ForkJoinTask processes.

    // Your code should look similar to the following pseudocode:

//    if (my portion of the work is small enough)
//        do the work directly
//    else
//        split my work into two pieces
//        invoke the two pieces and wait for the results

//    Wrap this code in a ForkJoinTask subclass, typically using one of its more specialized types,
//    either RecursiveTask (which can return a result) or RecursiveAction.

//    After your ForkJoinTask subclass is ready, create the object that represents all the work to be done and pass it to the invoke() method of a ForkJoinPool instance.

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        var myRecursiveAction = new MyRecursiveAction("test1234abc");
        forkJoinPool.invoke(myRecursiveAction);

        var customRecursiveTask = new MyRecursiveTask(new int[] {10, 20, 30, 40, 50, 60, 70});
        int result = forkJoinPool.invoke(customRecursiveTask);
        System.out.println(result);
    }

    // RecursiveAction for void tasks
    static class MyRecursiveAction extends RecursiveAction {

        private String workload = "";
        private static final int THRESHOLD = 4;

        public MyRecursiveAction(String workload) {
            this.workload = workload;
        }

        @Override
        protected void compute() {
            if (workload.length() > THRESHOLD) {
                ForkJoinTask.invokeAll(createSubtasks());
            } else {
                processing(workload);
            }
        }

        private List<MyRecursiveAction> createSubtasks() {
            List<MyRecursiveAction> subtasks = new ArrayList<>();

            String partOne = workload.substring(0, workload.length() / 2);
            String partTwo = workload.substring(workload.length() / 2);

            subtasks.add(new MyRecursiveAction(partOne));
            subtasks.add(new MyRecursiveAction(partTwo));

            return subtasks;
        }

        private void processing(String work) {
            String result = work.toUpperCase();
            System.out.println("This result - (" + result + ") - was processed by "
                    + Thread.currentThread().getName());
        }
    }
    
    // RecursiveTask<V> for tasks that return a value
    static class MyRecursiveTask extends RecursiveTask<Integer> {
        private int[] arr;

        private static final int THRESHOLD = 2;

        public MyRecursiveTask(int[] arr) {
            this.arr = arr;
        }

        @Override
        protected Integer compute() {
            if (arr.length > THRESHOLD) {
                return ForkJoinTask.invokeAll(createSubtasks())
                        .stream()
                        .mapToInt(ForkJoinTask::join)
                        .sum();
            } else {
                return processing(arr);
            }
        }

        private Collection<MyRecursiveTask> createSubtasks() {
            List<MyRecursiveTask> dividedTasks = new ArrayList<>();
            dividedTasks.add(new MyRecursiveTask(
                    Arrays.copyOfRange(arr, 0, arr.length / 2)));
            dividedTasks.add(new MyRecursiveTask(
                    Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
            return dividedTasks;
        }

        private Integer processing(int[] arr) {
            return Arrays.stream(arr)
                    .filter(a -> a > 10 && a < 27)
                    .map(a -> a * 10)
                    .sum();
        }
    }
}
