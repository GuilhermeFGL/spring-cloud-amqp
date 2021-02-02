package com.example.crud.service;

import org.springframework.stereotype.Service;

import com.example.crud.model.dto.ProductDto;

@Service
public interface ProductMessageService {

	void sendMessage(ProductDto productDto);

}
