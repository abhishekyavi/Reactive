package org.example.sec4;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class FluxgenrationUntil {

    private static final Logger log = LoggerFactory.getLogger(FluxgenrationUntil.class);

    public static void main(String[] args) {
        // demo1();
        demo2();

    }

    private static void demo1() {
        Flux.generate(SynchronousSink -> {
            var name = Util.getFaker().country().name();
            SynchronousSink.next(name);
            if (name.equalsIgnoreCase("canada")) {
                SynchronousSink.complete();
            }

        }).subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(SynchronousSink -> {
            var name = Util.getFaker().country().name();
            SynchronousSink.next(name);

        })
                .takeUntil(e -> e.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

}
