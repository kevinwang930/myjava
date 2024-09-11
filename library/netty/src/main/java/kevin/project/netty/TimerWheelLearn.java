package kevin.project.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName TimerWheelLearn
 * @Description Example of using Netty HashedWheelTimer
 * @Date 9/8/24
 **/
public class TimerWheelLearn {

    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer();

        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("Task executed!");
            }
        };

        // Schedule the task to execute after 5 seconds
        timer.newTimeout(task, 5, TimeUnit.SECONDS);

        // Ensure the program doesn't exit immediately
        try {
            Thread.sleep(6000); // Sleep long enough to ensure task gets executed
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            timer.stop();
        }
    }
}
