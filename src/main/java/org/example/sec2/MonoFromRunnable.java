package org.example.sec2;

import java.util.Arrays;
import java.util.List;
import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {

	private static final Logger log = LoggerFactory.getLogger(MonoFromRunnable.class);

	public static void main(String[] args) throws Exception {
		getProductname(2).subscribe(Util.subscriber("sub1"));
	}

	public static Mono<String> getProductname(int productid) {
		if (productid == 1) {
			return Mono.fromSupplier(() -> Util.getFaker().commerce().productName());
		}
		return Mono.fromRunnable(() -> notifyBussines(productid));
	}

	private static void notifyBussines(int productID) {
		log.info("Notifying bussiness on unavailable productId: {}", productID);
	}

}
