package kevin.project.closure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClosureLearn {
    int number = 0;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void testClosure() {
        int i = getNumber();
        executorService.execute(()->{
            System.out.println(i);
        });

    }

    private int getNumber() {
        return number++;
    }

    public static void main(String[] args) {
        ClosureLearn closureLearn = new ClosureLearn();
        closureLearn.testClosure();
        closureLearn.testClosure();
    }

}
