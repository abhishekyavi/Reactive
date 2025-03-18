package org.example.Flux.helper;

import java.util.List;
import java.util.stream.IntStream;
import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class NameGrenrator {


public static List<String> getNameLists(int count){
	return IntStream
		.range(1,count)
		.mapToObj(i->generateNames()).toList();
}


	public static Flux<String> getNameFlux(int count){
		return Flux.range(1,count)
			.map(i->generateNames());

	}

	private  static String generateNames(){
	Util.sleepSeconds(1);
		return Util.getFaker().name().firstName();


}

}
