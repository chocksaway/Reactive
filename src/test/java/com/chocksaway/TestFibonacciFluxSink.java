package com.chocksaway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestFibonacciFluxSink {
    @Test
    public void testFibonacciFluxSink() {
        Flux<Long> fibonacciGenerator = Flux.create(e -> {
            long current = 1, prev = 0;
            AtomicBoolean stop = new AtomicBoolean(false);
            e.onDispose(()->{
                stop.set(true);
                System.out.println("******* Stop Received ****** ");
            });
            while (current > 0) {
                e.next(current);
                System.out.println("generated " + current);
                long next = current + prev;
                prev = current;
                current = next;
            }
            e.complete();
        });
        List<Long> fibonacciSeries = new LinkedList<>();
        fibonacciGenerator.take(5).subscribe(t -> {
            System.out.println("consuming " + t);
            fibonacciSeries.add(t);
        });
        System.out.println(fibonacciSeries);
    }
}
