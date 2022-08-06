package concurrent;

public class RunnableCallable {
    // Runnable is the core interface provided for representing multithreaded tasks, and Java 1.5 provided Callable as an improved version of Runnable.
    // Both interfaces are designed to represent a task that can be run by multiple threads.
    // We can run Runnable tasks using the Thread class or ExecutorService, whereas we can only run Callables using the latter.

//    public interface Runnable {
//        public void run();
//    }

//    public interface Callable<V> {
//        V call() throws Exception;
//    }

    // callable improve: have return value, can easily propagate checked exceptions further

    // Clojure fns are Callable but not Runnable, i.e.:
    //
    // (instance? java.util.concurrent.Callable (fn []))
    // true
    // (instance? java.lang.Runnable (fn []))
    // false

    public static void main(String[] args) {
        // https://www.baeldung.com/java-runnable-callable
    }
}
