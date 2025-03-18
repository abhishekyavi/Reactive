package org.example.sec2.client;

import org.example.commons.AbstractHttpClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class ExternalServiceClient extends AbstractHttpClient{

	public Mono<String> getProductName(int productID){
		return httpClient.get().uri("/demo01/product/"+productID)
			.responseContent().asString().next();
	}
	}

