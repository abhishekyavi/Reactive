package org.example.sec1;

import java.time.Duration;
import org.example.sec1.Publisher.PublisherImp;
import org.example.sec1.Subscriber.SubscriberImpl;

public class Demo {

	public static void main(String[] args) throws Exception {
		//demo3();
		//demo4();
		//demo5();
		demo2();
	}



	public static void demo2() {
		var publisher = new PublisherImp();
		var susbcriber = new SubscriberImpl();
		publisher.subscribe(susbcriber);
		susbcriber.getSubscription().request(3);

	}

	public static void demo3() throws InterruptedException {
		var publisher = new PublisherImp();
		var susbcriber = new SubscriberImpl();
		publisher.subscribe(susbcriber);
		susbcriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2));
		susbcriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2));
		susbcriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2));
		susbcriber.getSubscription().request(3);
		Thread.sleep(Duration.ofSeconds(2));
		susbcriber.getSubscription().request(3);

		}

	public static void demo4() throws InterruptedException {
		var publisher = new PublisherImp();
		var susbcriber = new SubscriberImpl();
		publisher.subscribe(susbcriber);
		susbcriber.getSubscription().request(2);
		Thread.sleep(Duration.ofSeconds(2));
		susbcriber.getSubscription().cancel();
		susbcriber.getSubscription().request(3);


	}

	public static void demo5() throws InterruptedException {
		var publisher = new PublisherImp();
		var susbcriber = new SubscriberImpl();
		publisher.subscribe(susbcriber);
		susbcriber.getSubscription().request(12);



	}


}


