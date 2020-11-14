package com.example.fishservice.configuredRabbit;

import com.example.fishservice.entity.Fish;
import com.example.fishservice.entity.FishQuality;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class SendFishIdMessage {

    private  final RabbitTemplate rabbitTemplate;

    public SendFishIdMessage(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendFishIdToFisherMan(){
         String fishId = "5faaa13e74d3f2398f7cf879";
        rabbitTemplate.convertAndSend(ConfigureRabbitMq.FISH_EXCHANGE,ConfigureRabbitMq.ROUTING_KEY, fishId);
        log.info("got here");
    }
}
