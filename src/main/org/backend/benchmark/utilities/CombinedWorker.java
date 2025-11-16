package org.backend.benchmark.utilities;

import java.util.concurrent.Callable;

public class CombinedWorker implements Callable<String> {
    private final TrafficSimulator trafficSimulator;
    public CombinedWorker() {
        trafficSimulator = new TrafficSimulator();
    }
    @Override
    public String call() throws RuntimeException {
        try {
            String input = trafficSimulator.simulateIOLatency();
            return trafficSimulator.simulateCPULatency(input);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
