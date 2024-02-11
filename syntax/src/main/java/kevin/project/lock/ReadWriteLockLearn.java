package kevin.project.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockLearn {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    String value = "kevin";

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println("inside readLock");
            System.out.println(value);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            System.out.println("inside write lock");
            value = value + "kevin";
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockLearn readWriteLockLearn = new ReadWriteLockLearn();
        Thread readThread = new Thread(() -> {
            while (true) {
                readWriteLockLearn.read();
            }
        });
        Thread readThread2 = new Thread(() -> {
            while (true) {
                readWriteLockLearn.read();
            }
        });
        Thread writeThread = new Thread(() -> {
            while (true) {
                readWriteLockLearn.write();
            }
        });
        readThread.start();
        readThread2.start();
        Thread.sleep(1000L);
        writeThread.start();
        readThread.join();
        writeThread.join();
    }
}
