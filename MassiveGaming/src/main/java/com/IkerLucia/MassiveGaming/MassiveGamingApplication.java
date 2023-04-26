package com.IkerLucia.MassiveGaming;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< Updated upstream
=======
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
>>>>>>> Stashed changes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MassiveGamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassiveGamingApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
    	return new Queue("mensajes", false);
	}
<<<<<<< Updated upstream
=======
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
    	return new ConcurrentMapCacheManager("videojuegos", "consolas");
	}
>>>>>>> Stashed changes
}
