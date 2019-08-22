package com.tazz009.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ApiController {

	private static final String API_BASE_PATH = "/api";

	@GetMapping(API_BASE_PATH + "/images")
	Flux<Image> images() {
		Hooks.onOperatorDebug();
		return Flux.just(
				new Image(1, "learning-spring-boot-cover.jpg"),
				new Image(2, "learning-spring-boot-2nd-edition-cover.jpg"), 
				new Image(3, "bazinga.png"));
	}

	@PostMapping(API_BASE_PATH + "/images")
	Mono<Void> create(@RequestBody Flux<Image> images) {
		Hooks.onOperatorDebug();
		return images
				.map(image -> {
					log.info("We will save {} to a Reactive database soon!", image);
					return image;
				}).then();
	}
}
