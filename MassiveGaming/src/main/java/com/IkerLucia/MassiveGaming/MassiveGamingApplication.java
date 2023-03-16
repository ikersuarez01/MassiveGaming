package com.IkerLucia.MassiveGaming;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MassiveGamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassiveGamingApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
    	return new Queue("mensajes", false);
	}
}
