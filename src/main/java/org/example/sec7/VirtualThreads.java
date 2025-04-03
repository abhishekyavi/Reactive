package org.example.sec7;

import org.example.commons.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 * Key Concept:
 * Virtual threads are enabled using `System.setProperty("reactor.schedulers.defaultVirtualThreads", "true")`, allowing non-blocking execution with lightweight threads.
 */
public class VirtualThreads {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VirtualThreads.class);

    public static void main(String[] args) {
        System.setProperty("reactor.schedulers.defaultVirtualThreads", "true");

        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v))
                .doFirst(() -> log.info("First1 {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("First2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(5);
    }

}
