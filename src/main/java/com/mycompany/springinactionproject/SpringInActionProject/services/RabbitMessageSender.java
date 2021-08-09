package com.mycompany.springinactionproject.SpringInActionProject.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageSender {

    @Value("${app.rabbit.topicExchangeName}")
    private String topicExchangeName;
    
    @Value("${app.rabbit.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {

        rabbitTemplate.convertAndSend(topicExchangeName,
                routingKey,
                message);
    }

}
