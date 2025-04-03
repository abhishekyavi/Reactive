package org.example.sec6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

/*
 * Key Concepts:
 * 1. The `Flux.create` method is used to programmatically generate a cold publisher, emitting elements to each subscriber independently.
 * 2. The `sink.next()` method is called in a loop to emit three elements (0, 1, 2) to the subscribers.
 * 3. The `sink.complete()` method signals the completion of the stream after all elements are emitted.
 * 4. Two subscribers (`sub1` and `sub2`) independently consume the same stream, demonstrating the behavior of a cold publisher.
 * 5. SLF4J logging is used to track when the publisher is invoked, helping to observe the cold nature of the stream.
 */

public class ColdPublisher {
    private static final Logger log = LoggerFactory.getLogger(ColdPublisher.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
            log.info("invoked....");
            for (int i = 0; i < 3; i++) {
                sink.next(i);

            }
            sink.complete();
        });

        flux.subscribe(org.example.commons.Util.subscriber("sub1"));
        flux.subscribe(org.example.commons.Util.subscriber("sub2"));

    }

}
