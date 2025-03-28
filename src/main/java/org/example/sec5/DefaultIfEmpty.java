package org.example.sec5;

import java.util.Optional;

import org.example.commons.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultIfEmpty {
    public static void main(String[] args) {
        // fromMono();
        fromFlux();

    }

    private static void fromMono() {
        Mono.empty()
                .defaultIfEmpty("its a fallback value")
                .subscribe(Util.subscriber());
    }

    private static void fromFlux() {
        Flux.range(1, 10)
                // .defaultIfEmpty("its a fallback value")
                .filter(i -> 1 > 11)
                .defaultIfEmpty(50)
                .subscribe(Util.subscriber());
    }

}
