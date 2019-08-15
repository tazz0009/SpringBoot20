package com.tazz009.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ChapterController {

	@Autowired
	private ChapterRepository repository;
	
	@GetMapping("/chapters")
	public Flux<Chapter> listing() {
		return repository.findAll();
	}
	
}
