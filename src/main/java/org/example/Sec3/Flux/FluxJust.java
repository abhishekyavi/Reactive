package org.example.Sec3.Flux;

import org.example.commons.Util;
import reactor.core.publisher.Flux;

public class FluxJust {

	public static void main(String[] args) {
		Flux<Integer> flux = Flux.just(1,2,3,3,4,5,6);
		//flux.subscribe(Util.subscriber("sub1"));
		flux.filter(n->n%2==0).subscribe(i-> System.out.println(i));
		flux.filter(n->n%2!=0).subscribe(Util.subscriber("sunb2"));
		flux.map(a->a+"a").subscribe(Util.subscriber("sub3"));
		//flux.subscribe(Util.subscriber("sub2"));
		//flux.subscribe(Util.subscriber("sub3"));
	}

}
