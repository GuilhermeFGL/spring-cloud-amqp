package com.example.crud.service.implemantation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.crud.model.dto.ProductDto;
import com.example.crud.service.ProductMessageService;

@Component
public class ProductMessageServiceImpl implements ProductMessageService {

	@Value("${crud.rabbitmq.exchange}")
	private String exchange;

	@Value("${crud.rabbitmq.routingkey}")
	private String routingkey;

	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public ProductMessageServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void sendMessage(ProductDto productDto) {
		rabbitTemplate.convertAndSend(exchange, routingkey, productDto);
	}

}
