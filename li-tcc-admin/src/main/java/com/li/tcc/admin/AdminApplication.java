package com.li.tcc.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * li tcc start
 * 
 * @author yuan.li
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class AdminApplication {

	/**
	 * main start
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}