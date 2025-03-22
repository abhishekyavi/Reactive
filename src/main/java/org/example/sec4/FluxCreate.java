package org.example.sec4;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

public class FluxCreate {

    public static void main(String[] args) {

        // extracted();
        createFlux();

    }

    private static void extracted() {
        Flux.create(fluxsink -> {
            for (int i = 0; i < 5; i++) {
                fluxsink.next(Util.getFaker().country().name());
            }
            fluxsink.complete();

        }).subscribe(Util.subscriber());
    }

    private static void createFlux() {

        Flux.create(fluxsink -> {
            String country;
            do {
                country = Util.getFaker().country().name();
                fluxsink.next(country);

            } while (!country.equalsIgnoreCase("canada"));
            fluxsink.complete();
        }).subscribe(Util.subscriber());

    }
}
