package com.tazz009.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class LoadDatabase {

	@Bean
	CommandLineRunner init(ChapterRepository repository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Flux.just(
						new Chapter("Quick Start with Java"),
						new Chapter("Reactive Web with Spring Boot"),
						new Chapter("...and more!")
				)
				.flatMap(repository::save)
				.subscribe(c -> log.debug(c.toString()));
			}
			
		};
	}
	
}
