package com.example.demo.config;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import com.example.demo.bean.Foo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;


@Configuration
public class FooEndpointConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/foo"), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(streamFoo(), Foo.class));
    }


    private Flux<Foo> streamFoo() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> events = Flux.fromStream(Stream.generate(
                                        ()->new Foo(
                                        UUID.randomUUID().toString(),
                                        UUID.randomUUID().toString())));
        return Flux.zip(events, interval, (key, value) -> key);
    }
}
