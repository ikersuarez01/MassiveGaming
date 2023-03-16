package com.IkerLucia.InternalService;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.tools.json.JSONReader;

@Component
public class Consumer {
	@Autowired
	EmailSenderService emailService;
	@RabbitListener(queues = "compras", ackMode = "AUTO")
	public void recv (String message){
		//Mensaje mens = (Mensaje)SerializationUtils.deserialize(message);
		
		//emailService.sendSimpleEmail("ikersuarez01@gmail.com", "prueba", "eres la ostia");
		//System.out.println("Mensaje recibido: " + mens);
	}
}