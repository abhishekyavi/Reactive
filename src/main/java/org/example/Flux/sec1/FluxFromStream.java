package org.example.Flux.sec1;

import java.util.List;
import java.util.stream.Stream;
import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class FluxFromStream {

	public static void main(String[] args) {

		List <Integer> list = List.of(1, 2, 4, 3);

		Flux.fromStream(list.stream()).subscribe(Util.subscriber("sub1"));
		Flux.fromStream(list.stream()).subscribe(Util.subscriber("sub2"));

		//once the stream is utilized or comsumed  u cannot use it again

		//Flux.fromStream(list::stream).subscribe(Util.subscriber("sub2"));
		//**************************************
		//Flux from range
		Flux.range(3,10).subscribe(Util.subscriber());

		Flux.range(3,12).map(i->Util.getFaker().name().firstName()).subscribe(Util.subscriber());

	}

}
