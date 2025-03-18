package org.example.sec2;

import org.example.sec1.Subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribe {
	private static final Logger log= LoggerFactory.getLogger(MonoSubscribe.class);


	public static void main(String[] args) {
		var mono = Mono.just(1).map(i->i/0);

		mono.subscribe(i->log.info("recived {}",i),
			err->log.info("recived error",err),
			()->log.info("Complete"),
			subscription -> subscription.request(2)
			);
	}
}
