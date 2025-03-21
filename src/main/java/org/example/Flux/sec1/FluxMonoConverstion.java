package org.example.Flux.sec1;

import org.example.commons.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoConverstion {
    public static void main(String[] args) {
        monoToFlux();
        FluxToMono();
        FluxToMono1();
    }

    private static void monoToFlux() {
        var mono = getUserName(1);
        save(Flux.from(mono));
    }

    private static void FluxToMono() {
        var flux = Flux.range(1, 10);
        flux.next().subscribe(Util.subscriber());
    }

    private static void FluxToMono1() {
        var flux = Flux.range(1, 10);
        Mono.from(flux).subscribe(Util.subscriber());
    }

    private static Mono<String> getUserName(int id) {

        return switch (id) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("ivalid input"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
