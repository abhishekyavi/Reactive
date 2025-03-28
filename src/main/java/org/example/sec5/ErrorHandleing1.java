
package org.example.sec5;

import java.io.Flushable;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandleing1 {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandleing1.class);

    public static void main(String[] args) {

        // withFlux();
        // withMono();
        // ErrorChaining();
        // OnErrorComplete();
        OnErrorContinue();

    }

    private static void withFlux() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                // .onErrorReturn(-1)
                .onErrorReturn(ArithmeticException.class, -1)
                .subscribe(Util.subscriber());
    }

    private static void withMono() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                // .onErrorReturn(-1)
                // .onErrorReturn(ArithmeticException.class, -1)
                .onErrorResume(ex -> fallback1())
                .subscribe(Util.subscriber());

    }

    private static void ErrorChaining() {
        Mono.error(new ArithmeticException("opos"))
                // .onErrorReturn(-1)
                // .onErrorReturn(ArithmeticException.class, -1)
                .onErrorResume(ex -> fallback1())
                .subscribe(Util.subscriber());

    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(1, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(100, 1000));

    }

    private static void OnErrorComplete() {
        Mono.error(new RuntimeException())
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void OnErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)

                .onErrorContinue((ex, obj) -> log.error("==>{}", obj, ex))
                .subscribe(Util.subscriber());

    }
}
