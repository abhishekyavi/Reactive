package org.example.Flux.sec1;

import java.util.List;
import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class FluxFromIteratbale {

	public static void main(String[] args) {
		var list= List.of("a","b","c","d");
		Flux.fromIterable(list).subscribe(Util.subscriber("sub1"));
		Integer[] arr={1,2,3,4,5,6};
		Flux.fromArray(arr).subscribe(Util.subscriber());;
	}

}
