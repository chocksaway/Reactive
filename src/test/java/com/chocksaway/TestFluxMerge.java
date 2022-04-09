package com.chocksaway;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestFluxMerge {
    @Test
    public void testFluxMergeSubscribe() {
        final List<Index> subscribeList = new ArrayList<>();
        mergeAddOnePipeline().subscribe(subscribeList::add);

        assertTrue(subscribeList.stream().anyMatch(each -> each.attr >= 1 && each.attr <= 5));
    }

    Flux<Index> mergeAddOnePipeline() {
        Flux<Integer> fluxIntA = Flux.just(1, 2, 3, 4, 5);
        Flux<Integer> fluxIntB = Flux.just(1, 2, 3, 4, 5);
        AtomicInteger counter= new AtomicInteger();

        return fluxIntB.mergeWith(fluxIntA)
                .flatMap(each -> Flux.just(new Index(each, counter.incrementAndGet())))
                .doOnNext(System.out::println);
    }

    static class Index {
        private final int counter;
        private final int attr;

        public Index(int attr, int counter) {
            this.attr = attr;
            this.counter = counter;
        }

        @Override
        public String toString() {
            return counter + " " + attr;
        }
    }
}
