package org.example.sec5;

import javax.management.RuntimeErrorException;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class Handle {
    private static final Logger log = LoggerFactory.getLogger(Handle.class);

    public static void main(String[] args) {

        // demo1();
        demo2();

    }

    private static void demo1() {
        Flux.range(1, 10)
                .handle((item, siink) -> {
                    switch (item) {
                        case 1 -> siink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> siink.error(new RuntimeException("opps!"));
                        default -> siink.next(item);
                    }
                }).subscribe(Util.subscriber());
    }

    private static void demo2() {
        Flux.<String>generate(sink -> sink.next(Util.getFaker().country().name()))
                .handle((item, sink) -> {
                    sink.next(item);
                    if (item.equalsIgnoreCase("Canada")) {
                        sink.complete();
                    }
                }).subscribe(Util.subscriber());
    }

}
