package org.example.sec5;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Subscribe.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(i -> log.info("processing: " + i))
                .doOnComplete(() -> log.info("done"))
                .doOnError(err -> log.error("error: " + err.getMessage()))
                .subscribe();

    }

}
