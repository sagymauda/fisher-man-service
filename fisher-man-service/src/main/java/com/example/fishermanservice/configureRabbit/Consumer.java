package com.example.fishermanservice.configureRabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {


    @RabbitListener(queues = {ConfigurationRabbit.FISH_QUEUE})
    public void sendFishId(String fishId ){
        log.info("Handle The ID!!");
        log.info(fishId);
    }
}
