package kevin.project.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName ReEntrantLockLearn
 * @Description TODO
 * @Date 2024/8/7
 **/
public class ReEntrantLockLearn {

    public static void reEntrantLockLearn() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(() -> {
            lock.lock();
            System.out.println("inside thread lock");
            try {

                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("outside thread lock");
        });
        thread.start();
        lock.lock();
        System.out.println("inside lock");
        Thread.sleep(2000);
        lock.unlock();
        System.out.println("outside lock");
    }



    public static void main(String[] args) throws InterruptedException {
        reEntrantLockLearn();
    }
}
