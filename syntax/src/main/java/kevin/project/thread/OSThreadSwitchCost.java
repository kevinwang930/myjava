package kevin.project.thread;

// ⚠️ EXPENSIVE: OS Thread Context Switch Components

public class OSThreadSwitchCost {

    /*
     * 💰 COST BREAKDOWN (per context switch):
     *
     * 1. Kernel mode transition: ~100-500 ns
     * 2. Save/restore CPU registers: ~200-1000 ns
     * 3. Save/restore stack pointer: ~50-200 ns
     * 4. TLB (Translation Lookaside Buffer) flush: ~500-2000 ns
     * 5. Cache pollution/warming: ~1000-5000 ns
     * 6. Scheduler overhead: ~200-1000 ns
     *
     * 📊 TOTAL: ~2-10 microseconds per switch
     * 📈 Under load: Can increase to 20-50 microseconds
     */

    public static void demonstrateOSThreadCost() {
        int iterations = 1000;
        int workNanos = 100;

        // Create 2 OS threads
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                // Force context switch
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Small work unit
                doWork(workNanos); // 100ns of work
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < iterations; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                doWork(workNanos);
            }
        });

        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long duration = System.nanoTime() - startTime;
        System.out.println("duration: " + duration + " ns");
        long avgSwitchTime = duration / (iterations * 2);

        System.out.printf("Average context switch cost: %d ns%n", avgSwitchTime);
        // Typical result: 3000-8000 ns (3-8 microseconds)
    }

    private static void doWork(long nanos) {
        long start = System.nanoTime();
        while (System.nanoTime() - start < nanos) {
            // Busy wait for specified nanoseconds
        }
    }


    public static void main(String[] args) {
        demonstrateOSThreadCost();
    }
}
