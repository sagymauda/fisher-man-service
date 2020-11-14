package com.example.fishermanservice.configureRabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationRabbit {
    public static final String FISH_QUEUE = "fishIds";


    @Bean
    public Queue defaultParsingQueue(){
        return new Queue(FISH_QUEUE);
    }
}
