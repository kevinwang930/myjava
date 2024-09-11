package kevin.project.jmh;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName JMHRunner
 * @Description TODO
 * @Date 9/9/24
 **/
public class JMHRunner {

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchMark.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(2)
                .warmupTime(TimeValue.seconds(1))
                .measurementTime(TimeValue.seconds(1))
                .timeUnit(TimeUnit.NANOSECONDS)
                .jvmArgs("-Xmx4G") // Adjust as needed
                .build();

        new Runner(opt).run();

    }
}
