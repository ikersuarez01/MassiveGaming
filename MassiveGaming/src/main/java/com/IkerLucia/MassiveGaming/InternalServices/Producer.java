package com.IkerLucia.MassiveGaming.InternalServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.tools.json.JSONWriter;

@Component
public class Producer {
	
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    //@Scheduled(fixedRate = 5000)
	public void sendCompra(String usu, Double precio){
        String email = "ikersuarez01@gmail.com";
        String asunto = "Compra realizada";
        String texto = "Usted ha realizado correctamente una compra en Massive Gaming por " + precio.toString()+ "€";
        Mensaje mens = new Mensaje(email,asunto,texto);
        byte[] data = SerializationUtils.serialize(mens);
        rabbitTemplate.convertAndSend("compras", data);
        System.out.println("Mensaje Enviado");
	}

}