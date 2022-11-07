package io.dddbyexamples.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.dddbyexamples.cqrs.repository.CreditCardRepository;

@EnableWebMvc
@EnableKafka
@SpringBootApplication
public class CqrsApplication {

	private final CreditCardRepository creditCardRepository;

	public CqrsApplication(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CqrsApplication.class, args);
	}

}
