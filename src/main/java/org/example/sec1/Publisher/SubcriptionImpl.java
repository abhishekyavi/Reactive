package org.example.sec1.Publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubcriptionImpl implements Subscription {

	private static final Logger log = LoggerFactory.getLogger(SubcriptionImpl.class);
	private static final int MAX_ITEAMS = 10;
	private final Subscriber<? super String> subscriber;
	private final Faker faker;
	private boolean isCanclead;
	private int count = 0;

	SubcriptionImpl(Subscriber<? super String> subscriber) {
		this.subscriber = subscriber;
		this.faker = Faker.instance();
	}

	@Override
	public void request(long l) {
		if (isCanclead) {
			return;
		}
		log.info("Subcriber has reuested {} iteams", l);
		if(l>MAX_ITEAMS){
			subscriber.onError(new RuntimeException("validation failed>>>"));
			isCanclead=true;
			return;
		}
		for (int i = 0; i < l && count < MAX_ITEAMS; i++) {
			count++;
			subscriber.onNext(faker.internet().emailAddress());

		}

		if (count == MAX_ITEAMS) {
			log.info("no more teams to give");
			subscriber.onComplete();
			isCanclead = true;
		}


	}

	@Override
	public void cancel() {
		log.info("Is cancled called ");
		isCanclead = true;

	}
}
