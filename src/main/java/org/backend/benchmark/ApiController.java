package org.backend.benchmark;

import jakarta.annotation.PreDestroy;
import org.backend.benchmark.utilities.CombinedWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@SpringBootApplication
public class ApiController {
    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
    @RequestMapping("/getMessage")
    CompletableFuture<String> message(@RequestParam String name){
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for(int i=0; i<100; i++){
            futures.add(CompletableFuture.supplyAsync(() -> new CombinedWorker(name).call(), executor));
        }
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<String>> combinedResultsFromFutures = combinedFuture.thenApply(
                v->
                        futures.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList())
        );
        CompletableFuture<String> hashedNameAsFuture = combinedResultsFromFutures.thenApply(strings -> String.join(" ", strings));
        return hashedNameAsFuture.thenApply(hashedName -> name+":"+hashedName);
    }

    public static void main(String[] args){
        SpringApplication.run(ApiController.class, args);
    }

    @PreDestroy
    public void shutdown(){
        executor.shutdown();
    }
}

