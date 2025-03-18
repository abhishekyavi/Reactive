package org.example.commons;

import com.github.javafaker.Faker;
import java.time.Duration;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {

	private static  final Faker faker=Faker.instance();
	public static <T>Subscriber<T> subscriber(){
		return new DefaultSubscriber<>("");
	}

	public static <T>Subscriber<T> subscriber(String name){

		return new DefaultSubscriber<>(name);
	}
    public static Faker getFaker(){
		return faker;
	}

	public static void  sleepSeconds(int seconds){
		try {
			Thread.sleep(Duration.ofSeconds(3));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
/*	public static void main(String[] args) {
		var mono= Mono.just(1);
		mono.subscribe(subscriber("Sub1"));
		mono.subscribe(subscriber("Sub2"));
		mono.subscribe(subscriber("Sub3"));
	}*/


}
