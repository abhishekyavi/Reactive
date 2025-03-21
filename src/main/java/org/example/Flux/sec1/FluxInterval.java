package org.example.Flux.sec1;

import java.time.Duration;

import org.example.commons.Util;

import reactor.core.publisher.Flux;

public class FluxInterval {


    
    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(5))
        .map(e->Util.getFaker().name().firstName())
        .subscribe(Util.subscriber());

        Util.sleepSeconds(5);


        
    }
    
}
