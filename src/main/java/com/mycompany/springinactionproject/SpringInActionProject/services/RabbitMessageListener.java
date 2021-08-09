package com.mycompany.springinactionproject.SpringInActionProject.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageListener {
    
    
    @RabbitListener(queues = "${app.rabbit.queueName}")
    public void resceive(String message) {
        System.out.println(message);
    }

}
