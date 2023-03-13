package com.IkerLucia.MassiveGaming.InternalServices;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = "messages", ackMode = "AUTO")
	public void received(String message) {
		
		System.out.println("Message: "+message);
	}
}