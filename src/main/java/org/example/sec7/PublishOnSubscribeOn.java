package org.example.sec7;

import org.example.commons.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnSubscribeOn {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PublishOnSubscribeOn.class);

    public static void main(String[] args) {

        /*
         * Key Concept:
         * `subscribeOn` sets the scheduler for the subscription logic, determining
         * where the source of the stream executes. It applies globally to the entire
         * pipeline.
         * `publishOn` switches the execution context of downstream operators to a
         * specified scheduler. It only affects the operators declared after it in the
         * pipeline.
         * In this code:
         * - `subscribeOn(Schedulers.boundedElastic())` ensures the subscription logic
         * runs on a bounded elastic thread.
         * - `publishOn(Schedulers.parallel())` switches the downstream processing to a
         * parallel thread pool.
         * The order of these operators is important, as `subscribeOn` applies globally,
         * while `publishOn` is local to its position in the pipeline.
         */

        var flux = Flux.create(sink -> {
            for (int i = 0; i < 5; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v))
                .publishOn(Schedulers.parallel())
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(10);

    }

}
