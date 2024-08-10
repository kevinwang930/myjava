package kevin.project.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @version 1.0
 * @ClassName ObjectLockLearn
 * @Description TODO
 * @Date 2024/8/3
 **/
public class ObjectLockLearn {


    public static void monitorLearn() throws InterruptedException {
        Object lock = new Object();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        Thread thread = new Thread(() -> {
            try {
                synchronized(lock) {
                    System.out.println("start wait");
                    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                    lock.wait();
                    System.out.println("end wait");
                    System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(5000);
        synchronized (lock) {
            System.out.println("start notify");
            lock.notify();
            System.out.println("end notify");
        }
        thread.join();
    }




    public static void main(String[] args) throws InterruptedException {
        monitorLearn();
    }
}
