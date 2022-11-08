package com.kt.cqrs.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableScheduling
public class CqrsSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsSourceApplication.class, args);
	}
}
