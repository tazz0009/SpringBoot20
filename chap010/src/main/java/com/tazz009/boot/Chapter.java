package com.tazz009.boot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Chapter {

	@Id
	private String id;
	private String name;
	
	public Chapter(String name) {
		super();
		this.name = name;
	}
	
}
