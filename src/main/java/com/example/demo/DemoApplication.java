package com.example.demo;

import com.example.demo.bean.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Return/emit one resource every second.
	@Scheduled(fixedRate = 1000)
	public void getFoo() {

		Mono<Foo> fooMono = WebClient.create("http://localhost:8080")
				.get()
				.uri(uriBuilder -> uriBuilder.path("/foo").build())
				.retrieve()
				.bodyToMono(Foo.class);
		System.out.println("Foo Id:" + fooMono.block().getId() +  " Foo Name:" + fooMono.block().getName());
	}

}
