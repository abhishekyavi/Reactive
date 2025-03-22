package org.example.sec4;

import org.example.commons.Util;
import org.example.sec4.helper.NameGenrator;

import reactor.core.publisher.Flux;

public class FluxCreateRefactor {
    public static void main(String[] args) {
        var genrator = new NameGenrator();
        var flux = Flux.create(genrator);
        flux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {

            genrator.nameGenrate();

        }
    }

}
