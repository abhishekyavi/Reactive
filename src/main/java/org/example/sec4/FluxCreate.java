package org.example.sec4;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

/*
 * Key Concepts:
 * 1. The `Flux.create` method is used to programmatically generate a stream of data, allowing dynamic and manual emission of elements.
 * 2. The `fluxsink.next()` method is used to emit elements, such as random country names, to the stream.
 * 3. The `fluxsink.complete()` method signals the completion of the stream after all elements are emitted.
 * 4. The `createFlux` method demonstrates a loop that emits country names until "Canada" is encountered, showcasing conditional emission.
 * 5. The `extracted` method emits a fixed number of country names (5) to the stream, demonstrating a finite data generation example.
 */

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
