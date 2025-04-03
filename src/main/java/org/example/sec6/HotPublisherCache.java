package org.example.sec6;

import java.time.Duration;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

/*
 * Key Concepts:
 * 1. The `stockStream` is converted to a hot publisher using `.replay(1).autoConnect(0)`, caching the last emitted value for late subscribers.
 * 2. The `Flux.generate` method is used to emit random stock prices, simulating a live stock price stream.
 * 3. The `delayElements(Duration.ofSeconds(3))` introduces a delay of 3 seconds between each emitted stock price.
 * 4. Two subscribers (`sam` and `mike`) join the stream at different times, demonstrating the caching behavior of `.replay(1)`.
 * 5. The stream emits random stock prices between 1 and 100, showcasing dynamic data generation.
 */

public class HotPublisherCache {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HotPublisherCache.class);

    public static void main(String[] args) {
        var stockflux = stockStream().replay(1).autoConnect(0);

        Util.sleepSeconds(4);
        log.info("sam-joining");
        stockflux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);
        log.info("mike-joining");
        stockflux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.getFaker().random().nextInt(1, 100)))

                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("new price {}", price))
                .cast(Integer.class);

    }
}
