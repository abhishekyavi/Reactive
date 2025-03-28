package org.example.sec5;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

public class SwitchIfEmpty {

    public static void main(String[] args) {

        Flux.range(1, 10)

                .filter(i -> i > 11)
                .switchIfEmpty(fallback())

                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }
}
