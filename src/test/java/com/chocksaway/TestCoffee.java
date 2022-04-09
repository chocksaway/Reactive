package com.chocksaway;

import com.chocksaway.model.Coffee;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Author milesd on 09/04/2022.
 *
 * https://www.baeldung.com/reactive-streams-step-verifier-test-publisher
 *
 */
public class TestCoffee {
    @Test
    public void testFilterCoffee() {
        Flux<Coffee> coffee = Flux.just(new Coffee("good coffee", 100),
                    new Coffee("ok coffee", 75),
                    new Coffee("not so good coffee", 50),
                    new Coffee("mellow birds", 25)
                );

        Flux<String> coffeeName = coffee.filter(each -> each.getPrice() == 25)
                .flatMap(each -> Flux.just(each.getName())
                        .doOnNext(System.out::println));

        StepVerifier.create(coffeeName)
                        .expectNext("mellow birds")
                        .expectComplete()
                            .verify();
     }
}
