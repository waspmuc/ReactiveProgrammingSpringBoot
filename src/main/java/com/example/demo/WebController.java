package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Created by mkirsch on 18.04.18.
 */

@RestController
@CrossOrigin
public class WebController {

    @GetMapping("/hello")
    public Flux<String> hello() {

        final Integer count = 10;

        final Flux<String> interval = Flux.concat(Flux.interval(Duration.ofSeconds(4))
                        .map(i -> count - i)
                        .take(count)
                        .map(String::valueOf)
                        .map(s -> s + "\n")
                ,
                Mono.just("boom\n!"));
        return interval;
        //return Mono.just("Hello reactive world!");
    }

}
