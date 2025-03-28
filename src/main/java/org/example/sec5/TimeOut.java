package org.example.sec5;

import java.time.Duration;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class TimeOut {

    private static final Logger log = LoggerFactory.getLogger(TimeOut.class);

    public static void main(String[] args) {
        var mono = getProductname()
                .timeout(Duration.ofSeconds(2), fallbackService());
        // .onErrorReturn("fallback")
        // .onErrorResume(null)
        // .subscribe(Util.subscriber());

        mono.timeout(Duration.ofMillis(100))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<String> getProductname() {
        return Mono.fromSupplier(() -> "service-" + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofSeconds(1));

    }

    private static Mono<String> fallbackService() {
        return Mono.fromSupplier(() -> "fallbackService-" + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofSeconds(2));

    }

}
