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
                    int counter = 0;

                    while(counter <= 50) {
                        fluxSink.next(System.currentTimeMillis());
                        counter++;
                    }
                })
                .publish();

        hotFlux.take(5).subscribe(System.out::println);

        hotFlux.connect();


    }
}
