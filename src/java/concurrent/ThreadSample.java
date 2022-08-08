package concurrent;

public class ThreadSample {
    public static void main(String[] args) {
        // start a thread
        MyThread myThread = new MyThread();
        myThread.start();

        // Starting a Thread With a Runnable
        Runnable runnable = new MyRunnable(); // or an anonymous class, or lambda...
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class MyThread extends Thread {
    public void run(){
        System.out.println("MyThread running");
    }
}

class MyRunnable implements Runnable {
    public void run(){
        System.out.println("MyRunnable running");
    }
}
