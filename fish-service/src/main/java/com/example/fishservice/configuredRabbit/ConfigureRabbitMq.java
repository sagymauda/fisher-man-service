package com.example.fishservice.configuredRabbit;


import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class ConfigureRabbitMq {

    public static final String FISH_EXCHANGE = "fishId.exchange";
    public static final String FISH_QUEUE = "fishIds";
    public static final String ROUTING_KEY = "key";


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConvertor());
        return rabbitTemplate;
    }

    @Bean
    public  MessageConverter producerJackson2MessageConvertor() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public Declarables queueDeclarables() {
        Collection<Declarable> declarables = new ArrayList<>();
        TopicExchange messageExchange = new TopicExchange(FISH_EXCHANGE);
        Queue firstQueue = new Queue(FISH_QUEUE);
        declarables.add(firstQueue);
        declarables.add(messageExchange);

        declarables.addAll(getDeclarables(FISH_QUEUE, messageExchange));

        Queue messageQueue = declarables.stream().filter(declarable -> {
            boolean response = false;

            if (declarable instanceof Queue) {
                Queue queue = (Queue) declarable;

                response = queue.getName().equals(FISH_QUEUE);
            }
            return response;
        }).map(declarable -> (Queue) declarable).findFirst().get();

        declarables.add(BindingBuilder.bind(messageQueue).to(messageExchange).with(FISH_QUEUE));
        return new Declarables(declarables);


    }


    private Collection<Declarable> getDeclarables(String queueName, TopicExchange exchange) {
        Collection<Declarable> declarables = new ArrayList<>();
        String deadQueueName= queueName +".Dead";

        Queue deadQueue = new Queue(deadQueueName,true);
        declarables.add(deadQueue);

        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchange.getName());
        args.put("x-dead-letter-routing-key", deadQueueName);
        Queue queue = new Queue(queueName,true,false,false, args);
        declarables.add(BindingBuilder.bind(queue).to(exchange).with(queueName));
        declarables.add(BindingBuilder.bind(deadQueue).to(exchange).with(deadQueueName));
        return declarables;

    }

}