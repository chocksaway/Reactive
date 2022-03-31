package com.chocksaway;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.util.Arrays;

public class SimpleFibGenFlux {
    public static Flux<Long> fibGen() {
        return Flux.generate(
                () -> Tuples.of(0L, 1L),
                (state, sink) -> {
                    sink.next(state.getT1());
                    System.out.println("generated " + state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                });
    }


    // https://www.woolha.com/tutorials/project-reactor-using-subscribe-on-mono-and-flux
    public static void main(String[] args) {
        SimpleFibGenFlux.fibGen().subscribe(
                System.out::println,
                error ->  System.out.println(Arrays.toString(error.getStackTrace())),
                () -> System.out.println("done"),
                sub -> sub.request(3));
    }
}
