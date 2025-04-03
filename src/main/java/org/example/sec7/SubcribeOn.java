package org.example.sec7;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubcribeOn {
    private static final Logger log = LoggerFactory.getLogger(SubcribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v));

        /*
         * Key Concept:
         * The `subscribeOn(Schedulers.boundedElastic())` method is used to specify the
         * scheduler on which the subscription logic runs, enabling asynchronous
         * execution.
         */
        flux
                .doFirst(() -> log.info("First1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }
}
