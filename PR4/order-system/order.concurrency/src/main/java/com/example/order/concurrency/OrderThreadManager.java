package com.example.order.concurrency;

import com.example.order.processing.OrderProcessor;
import com.example.order.storage.model.Product;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class OrderThreadManager {
    private final ExecutorService executor;

    public OrderThreadManager(int threadPoolSize) {
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public <T extends Product> void processOrdersConcurrently(List<T> orders, Predicate<T> filterCondition) {
        orders.stream()
                .filter(filterCondition)
                .forEach(order -> {
                    Runnable orderTask = () -> OrderProcessor.processSingle(order);
                    executor.submit(orderTask);
                });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}