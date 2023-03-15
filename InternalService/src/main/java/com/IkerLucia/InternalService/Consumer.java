package com.IkerLucia.InternalService;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class Consumer {
	@Autowired
	EmailSenderService emailService;
	@RabbitListener(queues = "messages", ackMode = "AUTO")
	public void recv (String message){
		emailService.sendSimpleEmail("ikersuarez01@gmail.com", "prueba", "eres la ostia");
		System.out.println("Mensaje recibido: " + message);
	}
}