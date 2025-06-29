package kevin.project.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureLearn {

    public void completableFutureLearn() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Perform a long-running computation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result";
        });

        // Attach a callback to the CompletableFuture
        future.thenAccept(result -> {
            System.out.println("future 1 finish time: " + System.currentTimeMillis());
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            // Perform a long-running computation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result";
        });

        // Attach a callback to the CompletableFuture
        future2.thenAccept(result -> {
            System.out.println("future 2 finish time: " + System.currentTimeMillis());
        });


        CompletableFuture.allOf(future, future2)
                         .join();
    }

    public static void futureExceptionLearn() {
        CompletableFuture<Void> exception1 = CompletableFuture.runAsync(() -> {
            throw new RuntimeException("exception1");
        });
        CompletableFuture<Void> exception2 = CompletableFuture.runAsync(() -> {
            throw new RuntimeException("exception2");
        });
        CompletableFuture<Void> exception3 = CompletableFuture.runAsync(() -> {
            throw new RuntimeException("exception3");
        });
        CompletableFuture.allOf(exception1, exception2, exception3).join();
        System.out.println("finished all exception");
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Create a new CompletableFuture


//        FutureLearn futureLearn = new FutureLearn();
//        futureLearn.completableFutureLearn();
        futureExceptionLearn();
        System.out.println("main finished");
    }


}
