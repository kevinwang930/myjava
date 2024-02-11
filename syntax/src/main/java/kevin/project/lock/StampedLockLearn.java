package kevin.project.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockLearn {
    StampedLock lock = new StampedLock();
    String value = "kevin";

    public void read() {
        long stamp = lock.tryOptimisticRead();

        System.out.println("inside readLock");
        try {
            Thread.sleep(10000L);
            if (lock.validate(stamp)) {

                System.out.println(value);
            } else {
                System.out.println("read lock state invalid");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void write() {
        long stamp = lock.writeLock();
        try {
            System.out.println("inside write lock");
            value = value + "kevin";
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockLearn readWriteLockLearn = new StampedLockLearn();
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
