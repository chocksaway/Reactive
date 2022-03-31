package com.chocksaway;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSimpleFibGenFlux {
    @Test
    public void TestFibGenFiveElements() {
        AtomicInteger counter = new AtomicInteger();
        SimpleFibGenFlux.fibGen().take(3).subscribe(
                each -> counter.getAndIncrement()
        );

        assertEquals(3,counter);


    }
}
