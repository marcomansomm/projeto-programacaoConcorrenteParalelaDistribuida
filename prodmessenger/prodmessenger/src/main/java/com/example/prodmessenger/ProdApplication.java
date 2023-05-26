package com.example.prodmessenger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProdApplication {

	static final String directExchangeName = "direct-exchange";
	static final String routingKey = "rota-um";

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(directExchangeName,false, true);
	}


	public static void main(String[] args) {
		SpringApplication.run(ProdApplication.class, args);
	}
}