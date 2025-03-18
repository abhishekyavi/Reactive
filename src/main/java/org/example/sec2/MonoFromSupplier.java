package org.example.sec2;

import java.util.Arrays;
import java.util.List;
import org.example.commons.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromSupplier {
	private static final Logger log= LoggerFactory.getLogger(MonoFromSupplier.class);

	public static void main(String[] args) {
		int sum=getSum(Arrays.asList(1,2,3));
		Mono.fromSupplier(()->(sum)).subscribe(Util.subscriber());
	}

	public static int getSum(List<Integer> list){
		log.info("get sum of {}",list);
		 return list.stream().mapToInt(s->s).sum();
	}

}
