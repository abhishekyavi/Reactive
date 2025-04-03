package org.example.sec6;

import java.time.Duration;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(HotPublisher.class);

    public static void main(String[] args) {

        var movieflux = movieStream().share();
        Util.sleepSeconds(2);

        movieflux
                .take(4).subscribe(Util.subscriber("sam"));
        Util.sleepSeconds(3);
        movieflux
                .take(3)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);

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
