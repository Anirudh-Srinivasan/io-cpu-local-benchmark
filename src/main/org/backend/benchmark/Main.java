package org.backend.benchmark;

import static org.backend.benchmark.utilities.TrafficSimulator.simulateCPULatency;
import static org.backend.benchmark.utilities.TrafficSimulator.simulateIOLatency;

public class Main {
    public static void main(String[] args) throws Exception {
        long st = System.nanoTime();
        String result = driver();
        long et = System.nanoTime();
        double millis = (double) (et-st) / 1_000_000.0;
        System.out.printf("Time taken: %.2f" , millis);
    }
    private static String driver() throws InterruptedException {
        // returns a string post completion of the results
        // calls network + cpu back to back 50  times
        for (int i=0; i<50; i++) {
             simulateIOLatency();
             simulateCPULatency();
        }
        return "Hi";
    }
}
