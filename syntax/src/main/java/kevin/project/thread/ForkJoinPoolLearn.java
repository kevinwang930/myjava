package kevin.project.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ForkJoinPoolLearn {

    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPoolLearn forkJoinPoolLearn = new ForkJoinPoolLearn();
        forkJoinPoolLearn.forkJoinPoolLearn(8);
    }

    public  void forkJoinPoolLearn(int n) throws ExecutionException, InterruptedException {
        // Fibonacci number to calculate
        Logger logger = Logger.getLogger(ForkJoinPoolLearn.class.getName());

        // ForkJoinPool Implementation
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();
        ForkJoinTask<Integer> task = new FibonacciForkJoin(n);
        int forkJoinResult = forkJoinPool.invoke(task);
        long forkJoinTime = System.currentTimeMillis() - startTime;
        logger.info("ForkJoinPool fibonacci(" + n + ") = " + forkJoinResult + " in " + forkJoinTime + "ms");
        forkJoinPool.shutdown();
        // Common ThreadPool Executor Implementation

        startTime = System.currentTimeMillis();
        Future<Integer> future = executorService.submit(new FibonacciTask(n));
        int executorResult = future.get();
        long executorTime = System.currentTimeMillis() - startTime;
        logger.info("ExecutorService fibonacci(" + n + ") = " + executorResult + " in " + executorTime + "ms");

        executorService.shutdown();
    }

     class FibonacciTask implements Callable<Integer> {
        private final int n;

        FibonacciTask(int n) {
            this.n = n;
        }

        @Override
        public Integer call() {
            try {

                return fibonacci(n);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        private int fibonacci(int n) throws ExecutionException, InterruptedException {
            if (n <= 1) return n;
            Future<Integer> t1 = executorService.submit(new FibonacciTask(n - 1));
            Future<Integer> t2 = executorService.submit(new FibonacciTask(n - 2));

            return t1.get() + t2.get();
        }
    }

    static class FibonacciForkJoin extends RecursiveTask<Integer> {
        private final int n;

        FibonacciForkJoin(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return n;
            }

            FibonacciForkJoin f1 = new FibonacciForkJoin(n - 1);
            FibonacciForkJoin f2 = new FibonacciForkJoin(n - 2);
            f1.fork();
            return f2.compute() + f1.join();
        }
    }
}

