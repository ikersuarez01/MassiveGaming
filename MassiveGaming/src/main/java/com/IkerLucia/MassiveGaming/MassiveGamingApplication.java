package com.IkerLucia.MassiveGaming;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MassiveGamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MassiveGamingApplication.class, args);
	}
	
	private static final Log LOG = LogFactory.getLog(MassiveGamingApplication.class);
	
	@Bean
	public Queue myQueue() {
    	return new Queue("mensajes", false);
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
    	return new ConcurrentMapCacheManager("anuncios");
	}
}
