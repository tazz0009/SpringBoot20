package com.tazz009.boot;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExampleTests {

	@Test
	public void data1() {
		Flux.just("alpha", "bravo", "charlie");
	}

	@Test
	public void data2() {
		String[] items = new String[]{"alpha", "bravo", "charlie"};
		Flux.fromArray(items);
	}
	
	@Test
	public void data3() {
		List<String> items = Arrays.asList("alpha", "bravo", "charlie");
		Flux.fromIterable(items);
	}
	
	@Test
	public void data4() {
		Stream<String> items = Arrays.asList("alpha", "bravo", "charlie").stream();
		Flux.fromStream(items);
	}
	
	@Test
	public void data5() {
		Flux.just("alpha", "bravo", "charlie")
			.subscribe(System.out::println);
	}
	
	@Test
	public void data6() {
		Flux.just("alpha", "bravo", "charlie")
			.map(String::toUpperCase)
			.flatMap(s -> Flux.fromArray(s.split("")))
			.groupBy(String::toString)
			.sort((o1, o2) -> o1.key().compareTo(o2.key()))
			.flatMap(group -> Mono.just(group.key()).zipWith(group.count()))
			.map(keyAndCount -> keyAndCount.getT1() + " => " + keyAndCount.getT2())
			.subscribe(System.out::println);
	}
	
	@Test
	public void data7() {
		Flux.just(
			(Supplier<String>) () -> "alpha",
			(Supplier<String>) () -> "bravo",
			(Supplier<String>) () -> "charlie")
			.subscribe(supplier ->
				System.out.println(supplier.get()));
	}
}
