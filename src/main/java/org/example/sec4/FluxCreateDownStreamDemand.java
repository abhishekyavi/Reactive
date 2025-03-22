package org.example.sec4;

import org.example.commons.Util;
import org.example.sec1.Subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class FluxCreateDownStreamDemand {
    private static final Logger log = LoggerFactory.getLogger(FluxCreateDownStreamDemand.class);

    public static void main(String[] args) {

        // produceEarly();
        produceOnDemand();
    }

    private static void produceEarly() {
        var subcriber = new SubscriberImpl();

        Flux.<String>create(fluxsink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.getFaker().name().firstName();
                log.info("generated: {} ", name);
                fluxsink.next(name);
            }
            fluxsink.complete();
        }).subscribe(subcriber);
        Util.sleepSeconds(2);
        subcriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subcriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subcriber.getSubscription().cancel();
    }

    private static void produceOnDemand() {
        var subcriber = new SubscriberImpl();

        Flux.<String>create(fluxsink -> {
            fluxsink.onRequest(request -> {
                for (int i = 0; i < request && !fluxsink.isCancelled(); i++) {
                    var name = Util.getFaker().name().firstName();
                    log.info("generated: {} ", name);
                    fluxsink.next(name);
                }
            });

        }).subscribe(subcriber);
        Util.sleepSeconds(2);
        subcriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subcriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subcriber.getSubscription().cancel();
    }

}
