package org.example.sec4;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

public class FluxgeneratorwithState {
    public static void main(String[] args) {

        Flux.generate(() -> 0,
                (count, sink) -> {
                    var country = Util.getFaker().country().name();
                    sink.next(country);
                    count++;
                    if (count == 10 || country.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                    return count;
                }
        ).subscribe(Util.subscriber());

    }

}
