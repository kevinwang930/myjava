package kevin.project;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class SignalLearn {


    public static void main(String[] args) throws InterruptedException {
        long pid = ProcessHandle.current()
                                .pid();
        System.out.println("Process ID: " + pid);

        SignalHandler signalHandler = signal -> System.out.println("handling signal" + signal);
        Signal.handle(new Signal("INT"), signalHandler);
        Signal.handle(new Signal("TERM"), signalHandler);
        Thread.sleep(100000);
    }
}
