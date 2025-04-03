package org.example.sec7.client;

import org.example.commons.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

public class ExternalServiceClient extends AbstractHttpClient {

	private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

	/*
	 * Key Concept:
	 * The `publishOn(Schedulers.boundedElastic())` method ensures that the response processing happens on a bounded elastic thread, suitable for blocking or long-running tasks like HTTP calls.
	 */
	public Mono<String> getProductName(int productID) {
		return httpClient.get().uri("/demo01/product/" + productID)
				.responseContent()
				.asString()
				.doOnNext(m -> log.info("next: {}", m))
				.next()
				.publishOn(Schedulers.boundedElastic());
	}
}
