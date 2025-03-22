package org.example.sec4.helper;

import java.util.function.Consumer;

import org.example.commons.Util;

import reactor.core.publisher.FluxSink;

public class NameGenrator implements Consumer<FluxSink<String>> {

    private FluxSink sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        // TODO Auto-generated method stub
        this.sink = stringFluxSink;
        // throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    public void nameGenrate() {
        this.sink.next(Util.getFaker().name().firstName());
    }

}
