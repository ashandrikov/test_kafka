package com.example.kafka;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(KafkaProducer kafkaProducer) {
		return args -> {
			kafkaProducer.sendMessage("test", "Привет от Spring Boot веб-приложения!");
		};
	}

}
