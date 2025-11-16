package org.backend.benchmark;

import org.backend.benchmark.utilities.CombinedWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    static ExecutorService executor = Executors.newFixedThreadPool(16); // cpu_count*(1+(1/1)) = 8*2 = 16;
    public static void main(String[] args) throws Exception {

        long st = System.nanoTime();
        driver(st);
        executor.shutdown();
    }
    private static void driver(long st) throws InterruptedException {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for(int i=0; i<100; i++){
            futures.add(CompletableFuture.supplyAsync(() -> new CombinedWorker().call(), executor));
        }
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<String>> combinedResultsFromFutures = combinedFuture.thenApply(
                v->
                    futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList())
        );
        combinedResultsFromFutures.thenAccept(gather -> {
            String result = String.join(" ", gather);
            long et = System.nanoTime();
            double millis = (double) (et-st) / 1_000_000.0;
            System.out.printf("Time taken: %.2f" , millis);
            System.out.println("\nResult: " + result);
        });
    }
}
