package org.example.sec2;

import com.github.javafaker.Faker;
import java.util.concurrent.CompletableFuture;
import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromFuture {

	private static final Logger log = LoggerFactory.getLogger(MonoFromFuture.class);

	public static void main(String[] args) throws Exception {
	Mono.fromFuture(getName()).subscribe(Util.subscriber("sub1"));
	Util.sleepSeconds(2);
	}

	private static CompletableFuture<String> getName(){
		//log.info("generate name ");
		return CompletableFuture.supplyAsync(()->{
			log.info("generating name in file : ");
			return  Util.getFaker().name().firstName();
		});

		}
	}

