package org.example.sec2;

import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoEmptyError {
	private static final Logger log= LoggerFactory.getLogger(MonoEmptyError.class);

	public static void main(String[] args) {

		getUserName(4).subscribe(Util.subscriber("sub1"));
		//getUserName(3).subscribe(s->System.out.println(s),err->{});
	}

	private  static  Mono<String> getUserName(int id){

		return switch(id){
			case 1->Mono.just("sam");
			case 2->Mono.empty();
			default ->Mono.error(new RuntimeException("ivalid input"));

		};
	}
}
