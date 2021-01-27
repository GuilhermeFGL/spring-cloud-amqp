package com.example.crud.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crud.model.dto.ProductDto;

@Service
public interface ProductService {

	ProductDto findById(Long id);

	Page<ProductDto> findAll(Pageable pageable);

	ProductDto create(ProductDto productDto);

	ProductDto update(ProductDto productDto);

	void delete(Long id);

}
