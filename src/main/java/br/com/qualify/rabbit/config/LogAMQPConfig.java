package br.com.qualify.rabbit.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAMQPConfig {

    public static String EXCHANGE_NAME = "EnterpriseApplicationLog";

//    @Bean
//    public Exchange declareExchange() {
//        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
//                .durable(true)
//                .build();
//    }
}
