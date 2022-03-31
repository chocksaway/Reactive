package com.chocksaway;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Flow;


public class FibonacciSubscriber implements Subscriber<Long> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(10);
    }

    @Override
    public void onNext(Long fibNumber) {
        System.out.println(fibNumber);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        subscription = null;
    }

    @Override
    public void onComplete() {
        System.out.println("Finished");
        subscription = null;
    }
}
