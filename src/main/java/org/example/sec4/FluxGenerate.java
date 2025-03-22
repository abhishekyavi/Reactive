package org.example.sec4;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FluxGenerate {
    private static final Logger log = LoggerFactory.getLogger(FluxGenerate.class);

    public static void main(String[] args) {

        Flux.generate(SynchronousSink -> {
            SynchronousSink.next(1);
                //SynchronousSink.next(2);
           // SynchronousSink.complete();
        }).take(4)
        .subscribe(Util.subscriber());

    }

}
