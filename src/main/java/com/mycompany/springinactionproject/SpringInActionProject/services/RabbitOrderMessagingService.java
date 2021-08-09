package com.mycompany.springinactionproject.SpringInActionProject.services;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService {

    @Value("${app.rabbit.topicExchangeName}")
    private String topicExchangeName;
    @Value("${app.rabbit.queueName}")
    private String queueName;
    @Value("${app.rabbit.routingKey}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }
    
}
