package org.example.sec1.Publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImp implements Publisher<String> {

	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		var sunscription= new SubcriptionImpl(subscriber);
		subscriber.onSubscribe(sunscription);
	}
}

