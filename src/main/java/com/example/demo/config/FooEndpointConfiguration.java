package com.example.demo.config;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.util.UUID;

import com.example.demo.bean.Foo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;


@Configuration
public class FooEndpointConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/foo"), request -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(Foo.builder().id(UUID.randomUUID().toString()).name(UUID.randomUUID().toString()).build()),
                        Foo.class));
    }


}
