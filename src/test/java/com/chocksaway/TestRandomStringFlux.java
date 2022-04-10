package com.chocksaway;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRandomStringFlux {
    @Test
    public void testRandomStringFlux() {
        List<String> names = Arrays.asList("google", "abc", "fb", "stackoverflow");
        Flux<String> nameFlux = Flux.fromIterable(names)
                .filter(name -> name.length() > 5)
                .log()
                .map(each -> each.substring(0,2))
                .flatMap(toUpper)
                .take(2)
                .log();

        final List<String> values = new ArrayList<>();

        nameFlux.subscribe(values::add);

        assertEquals(2, values.size());
        assertTrue(values.contains("GO"));

    }

    Function<String, Publisher<String>> toUpper = s -> Flux.just(s.toUpperCase());
}
