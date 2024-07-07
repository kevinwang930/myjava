package kevin.project.thread;

public class ThreadLearn {

    public void singleThreadLearn() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                System.out.println(Thread.currentThread().isInterrupted());
            }
            System.out.println("inside thread");
        });
        t1.start();
        t1.interrupt();
        t1.join();
    }



    public static void main(String[] args) throws InterruptedException {
        ThreadLearn threadLearn = new ThreadLearn();
        threadLearn.singleThreadLearn();
        System.out.println("main finished");
    }
}
