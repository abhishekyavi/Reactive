package org.example.Flux.sec1;

import org.example.Flux.client.ExternalServiceClient;
import org.example.commons.Util;

public class NonBlocingStreamMessages {

    public static void main(String[] args) {
        var client= new ExternalServiceClient();
        client.getNames().subscribe(Util.subscriber());
        Util.sleepSeconds(8);
        
    }
    
}
