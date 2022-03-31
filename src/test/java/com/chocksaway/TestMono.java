package com.chocksaway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMono {
    @Test
    public void testSimpleMono() {
        Mono.fromSupplier(() -> 1);
        Mono.fromCallable(() -> new String[]{"color"}).subscribe(t -> System.out.println("received " + t));
        Mono.fromRunnable(() -> System.out.println(" ")).subscribe(t -> System.out.println("received " + t), null, () -> System.out.println("Finished"));
    }

    @Test
    public void testMonoFromFlux() {
        List<String> stringList = new ArrayList<>();
        Mono.from(Flux.just("Red", "Blue", "Yellow", "Black")).subscribe(t -> stringList.add(t));
        assertTrue(stringList.size() == 1);
        assertTrue(stringList.contains("Red"));
    }

    @Test
    public void TestFibGen() {

    }
}
