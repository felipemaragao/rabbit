package br.com.qualify.rabbit;

import br.com.qualify.rabbit.config.ProcessoAMQPConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class RabbitApplication {

	final static String retryQueue = "retry-queue";


	public static void main(String[] args) {
		SpringApplication.run(RabbitApplication.class, args);
	}



}
