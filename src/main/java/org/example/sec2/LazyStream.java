package org.example.sec2;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyStream {
	private static final Logger log= LoggerFactory.getLogger(LazyStream.class);

	public static void main(String[] args) {
		Stream.of(1).peek(i->log.info("recived {}:",i)).toList();
	}

}
