package com.example.payment.service.implemantation;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.payment.model.dto.ProductDto;
import com.example.payment.model.entity.Product;
import com.example.payment.repository.ProductRepository;

@Component
public class ProductReceiveServiceImpl {

	private ProductRepository productRepository;

	@Autowired
	public ProductReceiveServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RabbitListener(queues = { "${crud.rabbitmq.queue}" })
	public void receive(@Payload ProductDto productDto) {
		this.productRepository.save(Product.create(productDto));
	}

}
