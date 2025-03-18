package org.example.sec2;


import org.example.sec1.Subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class MonoJust {

	public static void main(String[] args) {
		var mono= Mono.just("Abhi");
		var subcriber = new SubscriberImpl();
		mono.subscribe(subcriber);
		subcriber.getSubscription().request(10);

		}


	}


