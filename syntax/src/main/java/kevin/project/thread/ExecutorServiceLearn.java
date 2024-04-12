package kevin.project.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorServiceLearn {

    ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public void singleFutureLearn() throws ExecutionException, InterruptedException {
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("inside task");
            return "result";

        });
        System.out.println(future.get());
    }

    public void multiFutureLearn() throws InterruptedException, ExecutionException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Future<String>> futures = list.stream().map(this::generateTask).map(executorService::submit).collect(Collectors.toList());

        System.out.println("after task invoke thread pool size " + executorService.getPoolSize() + " task size " + executorService.getQueue().size());
        for (Future<String> taskFuture : futures) {
            taskFuture.get();
        }
    }

    private Callable<String> generateTask(Integer arg) {
        return () -> {
            Thread.sleep(2000);
            System.out.println("inside task " + arg + " time " + System.currentTimeMillis());
            return String.valueOf(arg);
        };
    }

    public void stop() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(3,TimeUnit.SECONDS);
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorServiceLearn executorServiceLearn = new ExecutorServiceLearn();
        executorServiceLearn.singleFutureLearn();
//        executorServiceLearn.multiFutureLearn();
        executorServiceLearn.stop();
    }
}
