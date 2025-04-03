package org.example.sec6;

import java.time.Duration;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

/*
 * Key Concepts:
 * 1. The `movieStream` is converted to a hot publisher using `.publish().autoConnect(0)`, starting the stream immediately without requiring subscribers.
 * 2. The stream emits elements with a delay of 1 second per element using `delayElements(Duration.ofSeconds(1))`.
 * 3. Two subscribers (`sam` and `mike`) consume the stream at different times, showcasing the behavior of a hot publisher.
 * 4. The `Flux.generate` method is used to emit movie scenes sequentially, maintaining state across emissions.
 * 5. SLF4J logging is used to track requests and emitted scenes, aiding in debugging and understanding the flow.
 */

public class HotPublisherAutoConnect {
    private static final Logger log = LoggerFactory.getLogger(HotPublisherAutoConnect.class);

    public static void main(String[] args) {

        var movieflux = movieStream().publish().autoConnect(0);
        Util.sleepSeconds(2);

        movieflux
                .take(1).subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(3);
        movieflux
                .take(2)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);

    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                () -> {
                    log.info("recevied request");
                    return 1;
                },
                (state, sink) -> {
                    var scene = "movie scene" + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    return ++state;
                }

        ).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);

    }
}
