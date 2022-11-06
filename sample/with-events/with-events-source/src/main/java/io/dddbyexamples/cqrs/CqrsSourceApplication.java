package io.dddbyexamples.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SuppressWarnings("deprecation")
@EnableWebMvc
@SpringBootApplication
@EnableScheduling
@EnableBinding(Source.class)
public class CqrsSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsSourceApplication.class, args);
	}
}
