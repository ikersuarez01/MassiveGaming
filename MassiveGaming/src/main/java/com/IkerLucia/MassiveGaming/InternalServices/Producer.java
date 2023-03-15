package com.IkerLucia.MassiveGaming.InternalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class Producer {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    //@Scheduled(fixedRate = 5000)
	public void send(){
        String message = "Hello World!";
        rabbitTemplate.convertAndSend("messages", message);
        System.out.println("Mensaje Enviado");
	}

}