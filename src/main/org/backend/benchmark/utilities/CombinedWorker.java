package org.backend.benchmark.utilities;

public class CombinedWorker implements Runnable {
    private String output;
    private final TrafficSimulator trafficSimulator;
    public CombinedWorker() {
        trafficSimulator = new TrafficSimulator();
    }
    public void run() {
        try {
            String input = trafficSimulator.simulateIOLatency();
            output = trafficSimulator.simulateCPULatency(input);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public String getOutput() {
        return output;
    }
}
