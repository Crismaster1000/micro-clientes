package com.stephano.microclientes.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "clientes.exchange";
    public static final String ROUTING_KEY = "clientes.events";

    @Bean
    public TopicExchange clientesExchange() {
        return new TopicExchange(EXCHANGE);
    }

}
