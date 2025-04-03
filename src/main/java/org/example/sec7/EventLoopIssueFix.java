package org.example.sec7;

import org.example.commons.Util;
import org.example.sec7.client.ExternalServiceClient;
import reactor.core.scheduler.Schedulers;

public class EventLoopIssueFix {
    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        // System.out.println("starting");
        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .publishOn(Schedulers.boundedElastic())
                    .map(e -> Process(e))
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(5);

    }

    private static String Process(String input) {
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
