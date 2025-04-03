package org.example.sec7;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

/*
 * Key Concepts:
 * 1. The `Flux.create` method is used to programmatically generate a stream of data, emitting three elements (0, 1, 2) to the stream.
 * 2. The `sink.complete()` method signals the completion of the stream after all elements are emitted.
 * 3. The `doOnNext` operator is used to log each emitted element as it is received by the stream.
 * 4. A `Runnable` is created to subscribe a subscriber (`sub3`) to the stream, demonstrating asynchronous execution.
 * 5. The `Thread.ofPlatform().start(runnable)` method is used to run the subscription in a separate thread, showcasing non-blocking behavior.
 */

public class DefaultBehaviour {
    private static final Logger log = LoggerFactory.getLogger(DefaultBehaviour.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating {}", i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext(v -> log.info("receiving", v));
        // flux.subscribe(Util.subscriber("sub1"));
        // flux.subscribe(Util.subscriber("sub2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub3"));
        Thread.ofPlatform().start(runnable); // This will block the main thread until the runnable completes.
    }

}
