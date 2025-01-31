/*
 * 
 */
package com.bank.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class RabbitMQConfig.
 */
@Configuration
public class RabbitMQConfig {

	/** The Constant QUEUE_NAME. */
	public static final String QUEUE_NAME = "makeTransferQueue";

	/** The Constant EXCHANGE_NAME. */
	public static final String EXCHANGE_NAME = "makeTransferExchange";

	/** The Constant ROUTING_KEY. */
	public static final String ROUTING_KEY = "makeTransferRoutingKey";

	/**
	 * Queue.
	 *
	 * @return the queue
	 */
	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

	/**
	 * Exchange.
	 *
	 * @return the direct exchange
	 */
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	/**
	 * Binding.
	 *
	 * @param queue    the queue
	 * @param exchange the exchange
	 * @return the binding
	 */
	@Bean
	public Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	/**
	 * Json message converter.
	 *
	 * @return the message converter
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
