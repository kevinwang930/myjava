package kevin.project.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ReactorLearn {

    public static void fluxLearn() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        Flux<String> many = Flux.fromIterable(list);
        many.subscribe(System.out::println);
        System.out.println();
    }

    public static void delayLearn() {
        Flux<String> stringFlux = Mono.just("Hello ").concatWith(Mono.just("world")).delaySubscription(Duration.ofMillis(500L));
        stringFlux.subscribe(System.out::println);
    }


    public static void main(String[] args) throws InterruptedException {
        fluxLearn();
        delayLearn();
        Thread.sleep(1000L);
    }

}
