package org.example.sec4;

import java.util.ArrayList;

import org.example.commons.Util;
import org.example.sec4.helper.NameGenrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class FluxSinkThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        // demo1();
        demo2();
    }

    private static void demo1() {
        var list = new ArrayList<Integer>();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            // new Thread(runnable).start(); // java 17
            Thread.ofPlatform().start(runnable); // java 21
        }

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());

    }

    private static void demo2() {
        var list = new ArrayList<String>();

        var genrator = new NameGenrator();
        var flux = Flux.create(genrator);
        flux.subscribe(name -> list.add(name));

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                genrator.nameGenrate();
            }
        };
        for (int i = 0; i < 10; i++) {
            // new Thread(runnable).start(); // java 17
            Thread.ofPlatform().start(runnable); // java 21
        }

        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());

    }

}
