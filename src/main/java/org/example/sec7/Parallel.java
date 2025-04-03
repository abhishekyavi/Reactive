package org.example.sec7;

import org.example.commons.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Parallel {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Parallel.class);

    public static void main(String[] args) {

        /*
         * Key Concept:
         * The `parallel()` method enables parallel processing of data, while
         * `runOn(Schedulers.parallel())` assigns the tasks to a parallel thread pool.
         * The `sequential()` method converts the parallelized stream back to a
         * sequential stream for further processing.
         */

        Flux.range(1, 10)
                .parallel(1)
                .runOn(Schedulers.parallel())
                .map(i -> process(i))
                .sequential()
                .map(e -> e + " processed")
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5); // Sleep for 5 seconds to allow the asynchronous processing to complete before
                              // exiting the program

    }

    private static int process(int input) {
        log.info("time consuming task ", input);
        Util.sleepSeconds(1);
        return input * 2;
    }

}
