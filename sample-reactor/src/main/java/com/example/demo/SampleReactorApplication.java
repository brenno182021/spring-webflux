package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class SampleReactorApplication {

	public static void main(String[] args) {

		Flux.fromIterable(List.of(1,2,3,4,5,6))
				.map(n -> Map.of(n.getClass().toString(), n))
				.collectList()
				.map(list -> {
					var map = new HashMap<>();
					list.forEach(l -> map.put(("class java.lang.Integer" + l.get("class java.lang.Integer")), l.get("class java.lang.Integer")));
					return map;
				})
				.doOnNext(System.out::println)
				.subscribe();
//		SpringApplication.run(SampleReactorApplication.class, args);
	}

}
