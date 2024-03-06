package concurrent;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        threadLocal.set(41);

        Runnable task = () -> {
            int value = threadLocal.get();
            System.out.println("ThreadLocal value in new thread: " + value);
        };

        Thread thread = new Thread(task);
        thread.start();

        int value = threadLocal.get();
        System.out.println("ThreadLocal value in main thread: " + value);
    }
}
