package org.backend.benchmark;

import org.backend.benchmark.utilities.CombinedWorker;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        long st = System.nanoTime();
        String result = driver();
        long et = System.nanoTime();
        double millis = (double) (et-st) / 1_000_000.0;
        System.out.printf("Time taken: %.2f" , millis);
        System.out.println("\nResult: " + result);
    }
    private static String driver() throws InterruptedException {
        // returns a string post completion of the results
        // calls network + cpu back to back 50  times
        ArrayList<String> gather = new ArrayList<String>();
        ArrayList<CombinedWorker> runnables = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i=0; i<100; i++) {
            CombinedWorker worker = new CombinedWorker();
            Thread t = new Thread(worker);
            t.start();
            threads.add(t);
            runnables.add(worker);
        }
        for(int i=0; i<100; i++) {
            Thread t = threads.get(i);
            t.join();
            CombinedWorker worker = runnables.get(i);
            gather.add(worker.getOutput());
        }
        return String.join(" ", gather);
    }
}
