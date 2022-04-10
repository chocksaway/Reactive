package com.chocksaway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * Author milesd on 10/04/2022.
 *
 * https://www.baeldung.com/reactor-core
 */
public class TestHotFlux {
    @Test
    public void testHotFlux() {
        ConnectableFlux<Object> hotFlux = Flux.create(fluxSink -> {
                    while(true) {
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .publish();

        hotFlux.subscribe(System.out::println);

        hotFlux.connect();


    }
}
