package kevin.project.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @ClassName BenchMark
 * @Description Benchmark testing for List and HashMap operations
 * @Date 9/9/24
 **/
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class WheelTimerBenchMark {

    @Param({"100", "10000", "1000000"})
    private int size;

    private List<Integer> list;
    private Map<Integer, Integer> map;

    @Setup
    public void setup() {
        list = new ArrayList<>();
        map = new ConcurrentHashMap<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
            map.put(i, i);
        }
    }

    @Benchmark
    public void sequentialListAccess(Blackhole bh) {
        for (int i = 0; i < list.size(); i++) {
            bh.consume(list.get(i));
        }
    }

    @Benchmark
    public void sequentialMapAccess(Blackhole bh) {
        for (int i = 0; i < map.size(); i++) {
            bh.consume(map.get(i));
        }
    }

    @Benchmark
    public void randomListAccess(Blackhole bh) {
        int index = ThreadLocalRandom.current().nextInt(size);
        bh.consume(list.get(index));
    }

    @Benchmark
    public void randomMapAccess(Blackhole bh) {
        int key = ThreadLocalRandom.current().nextInt(size);
        bh.consume(map.get(key));
    }


    @Benchmark
    public void listAppend() {
        list.add(1000);
    }

    @Benchmark
    public void mapAppend() {
        map.put(1000, 1000);
    }



}
