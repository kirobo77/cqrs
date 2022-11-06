package io.dddbyexamples.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CqrsSinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsSinkApplication.class, args);
	}
}
