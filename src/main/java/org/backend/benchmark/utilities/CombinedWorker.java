package org.backend.benchmark.utilities;

import java.util.concurrent.Callable;

public class CombinedWorker implements Callable<String> {
    private final TrafficSimulator trafficSimulator;
    private final String input;
    public CombinedWorker(String nameToHash) {
        trafficSimulator = new TrafficSimulator();
        this.input = nameToHash;
    }
    @Override
    public String call() throws RuntimeException {
        try {
            trafficSimulator.simulateIOLatency();
            return trafficSimulator.simulateCPULatency(input);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
