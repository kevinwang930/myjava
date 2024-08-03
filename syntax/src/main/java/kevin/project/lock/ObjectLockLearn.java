package kevin.project.lock;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @ClassName ObjectLockLearn
 * @Description TODO
 * @Date 2024/8/3
 **/
public class ObjectLockLearn {


    public static void objectLockLearn() throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            try {
                synchronized(lock) {
                    System.out.println("start wait");
                    lock.wait();
                    System.out.println("end wait");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(1000);
        synchronized (lock) {
            lock.notify();
        }
        thread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        objectLockLearn();
    }
}
