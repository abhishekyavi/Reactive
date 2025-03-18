package org.example.commons;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract  class  AbstractHttpClient {
private static final String  BASE_URL="http://localhost:7070";
protected  final HttpClient httpClient;

	protected AbstractHttpClient() {
		var looprescources= LoopResources.create("th1",1,true);
		this.httpClient = HttpClient.create().runOn(looprescources).baseUrl(BASE_URL);
	}

	/*protected AbstractHttpClient(HttpClient httpClient) {
		var looprescources= LoopResources.create("Mythread",1,true);
		this.httpClient = HttpClient.create().runOn(looprescources).baseUrl(BASE_URL);
	}*/

}
