package kevin.project.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterQueue {
    private final Lock queueLock = new ReentrantLock(true); // Fair lock

    public void printJob(Object document) {
        queueLock.lock();
        try {
            // Simulate the time taken to print the document
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        } finally {
            System.out.println(Thread.currentThread().getName() + " printed the document successfully.");
            queueLock.unlock();
        }
    }

    public static void main(String[] args) {
        PrinterQueue printerQueue = new PrinterQueue();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is going to print a document");
                printerQueue.printJob(new Object());
            }, "Thread " + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}