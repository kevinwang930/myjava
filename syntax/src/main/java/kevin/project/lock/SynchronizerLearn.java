package kevin.project.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @ClassName ReEntrantLockLearn
 * @Description TODO
 * @Date 2024/8/7
 **/
public class SynchronizerLearn {

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

    public static void conditionObjectLearn() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("before await");
                condition.await();
                System.out.println("after await");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();
        thread.join();
    }

    public static void cyclicBarrierLearn() throws InterruptedException {
        int NUM_THREADS = 3;
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> {
            System.out.println("All threads have reached the barrier, executing barrier action!");
        });


        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(new Worker(i,barrier)).start();
        }

        Thread.sleep(10000);




    }


    public static void main(String[] args) throws InterruptedException {
//        reEntrantLockLearn();
        conditionObjectLearn();
        cyclicBarrierLearn();
    }

    static class Worker implements Runnable {
        private final int id;
        private final CyclicBarrier barrier;

        Worker(int id,CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " is doing some work");
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println("Thread " + id + " is waiting at the barrier");
                barrier.await();
                System.out.println("Thread " + id + " has crossed the barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
