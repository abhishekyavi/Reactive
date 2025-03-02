package org.example.Flux.sec1;

import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class FluxLog {

	public static void main(String[] args) {
		Flux.range(1,5)
			.log("1st_log")
			.map(i->Util.getFaker().name().firstName())
			.log("2nd_log")
			.subscribe(Util.subscriber());
	}

}
