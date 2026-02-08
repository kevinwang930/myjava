package kevin.project.classloader;

import org.apache.commons.lang3.time.StopWatch;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * @version 1.0
 * @ClassName classLoaderLearn
 * @Description TODO
 * @Date 8/19/25
 **/
public class classLoaderLearn {

    public static void main(String[] args) throws Exception {



        int i = 0;
        while (i++ < 5000) {
            dojob(i);

        }
    }

    private static void dojob(int i) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String oclassName = "test.%s";
        String className = String.format(oclassName, "MyClass" + i);
        String ojavaSource =
                "package test; public class %s { public static void test() { System.out.println(\"Hello World\"); } }";
        String javaSource = String.format(ojavaSource, "MyClass" + i);
        ClassLoader parentClassLoader = MemoryClassLoader.class.getClassLoader();
        MemoryClassLoader memoryClassLoader = new MemoryClassLoader(parentClassLoader);
        // use a parent class loader that can resolve the classes referenced in the source
        Class<?> clazz = memoryClassLoader.compileAndLoad(className, javaSource);
        clazz.getMethod("test").invoke(null);
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool : pools) {
            if (!pool.getName().equals("Metaspace")) {
                continue;
            }
            MemoryUsage u = pool.getUsage();
            System.out.printf("%d- %d  %s - used: %d, committed: %d, max: %d%n",
                              i,
                              stopWatch.getTime(),
                              pool.getName(),
                              u.getUsed(),
                              u.getCommitted(),
                              u.getMax());
        }
        memoryClassLoader = null;
        clazz = null;

    }

}
