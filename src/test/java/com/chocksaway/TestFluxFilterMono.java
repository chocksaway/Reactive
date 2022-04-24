package com.chocksaway;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Author milesd on 24/04/2022.
 */
public class TestFluxFilterMono {

    @Test
    public void testPerson() {
        Person john = new Person("John", 22);
        StepVerifier.create(Flux.just(john.getName(), john.getAge()))
                    .expectNext("John", 22)
                    .expectComplete()
                    .verify();
    }

    @Test
    public void testCallParse() {
        StepVerifier.create(ParsePerson.parse())
                .expectNext("John")
                .expectNext("Mary")
                .verifyComplete();
    }
}

class ParsePerson {
    static Flux<String> parse() {
        Flux<Person> peopleList = Flux.just(new Person("John", 22), new Person("Mary", 33));

        return peopleList
                .filter(each -> each.getAge() > 20)
                .map(Person::getName);
    }
}

class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

