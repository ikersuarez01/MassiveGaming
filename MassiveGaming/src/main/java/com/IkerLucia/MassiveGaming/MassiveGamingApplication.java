package com.IkerLucia.MassiveGaming;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;


@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableHazelcastHttpSession
public class MassiveGamingApplication {
	

	private static final Log LOG = LogFactory.getLog(MassiveGamingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MassiveGamingApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
    	return new Queue("mensajes", false);
	}
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
    	return new ConcurrentMapCacheManager("videojuegos", "consolas");
	}
	@Bean
	public Config config() {

	    Config config = new Config();

	    JoinConfig joinConfig = config.getNetworkConfig().getJoin();

	    joinConfig.getMulticastConfig().setEnabled(false);
	    joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Arrays.asList("massivegaming-web-1", "massivegaming-web-2"));

	    return config;
	}
}
