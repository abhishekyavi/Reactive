package org.example.sec7;

import org.example.commons.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOn {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PublishOn.class);

    /*
     * Key Concept:
     * The `publishOn(Schedulers.parallel())` method switches the execution context
     * of downstream operators to a specified scheduler, enabling asynchronous
     * processing.
     * 
     * Difference between `publishOn` and `subscribeOn`:
     * - `publishOn` affects the execution context of operators downstream from
     * where it is declared.
     * - `subscribeOn` affects the execution context of the subscription logic and
     * applies globally to the entire pipeline.
     */

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 5; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v))
                .publishOn(Schedulers.parallel())
                .doFirst(() -> log.info("First1"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(10); // Sleep for 5 seconds to allow the asynchronous processing to complete before
    }

}
