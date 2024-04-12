package kevin.project.thread;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolLearn {

    public static void forkJoinPoolLearn() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(() -> {
            System.out.println("hello");
        });
    }


    public static void main(String[] args) {
        forkJoinPoolLearn();
    }
}
