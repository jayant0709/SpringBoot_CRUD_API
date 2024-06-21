package com.mongotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mongotest")
public class MongoCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoCrudApplication.class, args);
	}
}
