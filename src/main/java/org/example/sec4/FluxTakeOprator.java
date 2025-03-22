package org.example.sec4;

import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class FluxTakeOprator {
    public static void main(String[] args) {

        // take();
        // takewhile();
        takeUntil();
    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    // give the value as loong as the condition is true.
    private static void takewhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    // give the value as soon as the condition is true( do not go to the entire
    // range).+ give the value
    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

}
