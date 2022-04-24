package com.chocksaway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class TestEmptyMono {
    @Test
    public void testEmptyMono() {
        Mono<String> monoString = processEmptyMono();

        Mono<String> result = monoString
                .map(each -> each)
                .log()
                .switchIfEmpty(Mono.empty())
                .doOnNext(System.out::println);

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void testyMono() {
        Mono<String> monoString = processMono();

        Mono<String> result = monoString
                .map(each -> each)
                .log()
                .switchIfEmpty(Mono.empty())
                .doOnNext(System.out::println);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }



    protected Mono<String> processEmptyMono() {
        return Mono.empty();
    }

    protected Mono<String> processMono() {
        return Mono.just("hello");
    }
}
