package org.example.sec2;

import org.example.commons.Util;
import org.example.sec2.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingIO {

	private static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);

	public static void main(String[] args) {
		var client = new ExternalServiceClient();
		log.info("starting");
		for (int i = 1; i < 10; i++) {
			client.getProductName(i)
				.subscribe(Util.subscriber("sub:" + i));
		}
		Util.sleepSeconds(2);
	}

}
