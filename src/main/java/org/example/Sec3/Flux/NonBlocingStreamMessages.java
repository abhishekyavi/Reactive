package org.example.Sec3.Flux;

import org.example.Sec3.client.ExternalServiceClient;
import org.example.commons.Util;

public class NonBlocingStreamMessages {

    public static void main(String[] args) {
        var client= new ExternalServiceClient();
        client.getNames().subscribe(Util.subscriber());
        Util.sleepSeconds(8);
        
    }
    
}
