package org.example.sec7;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * Key Concept:
 * Multiple subscriptions to the same `Flux` are demonstrated, with each subscription running on a separate thread using `Thread.ofPlatform().start(runnable)`, showcasing concurrent execution.
 */
public class MultiPleSubsCription {
    private static final Logger log = LoggerFactory.getLogger(SubcribeOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v))
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));
        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        Util.sleepSeconds(5); // Sleep for 5 seconds to allow the asynchronous processing to complete before
                              // the main thread exits.
    }
}
