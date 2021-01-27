package com.example.crud.service.implemantation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.dto.ProductDto;
import com.example.crud.model.entity.Product;
import com.example.crud.repository.ProductRepository;
import com.example.crud.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	private static final String MSG_NO_RECORDS_FOUND = "No records found for this ID";

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductDto findById(Long id) {
		return ProductDto.create(this.find(id));
	}

	@Override
	public Page<ProductDto> findAll(Pageable pageable) {
		return this.productRepository.findAll(pageable).map(ProductDto::create);
	}

	@Override
	public ProductDto create(ProductDto productDto) {
		return this.persist(productDto);
	}

	@Override
	public ProductDto update(ProductDto productDto) {
		this.find(productDto.getId());

		return this.persist(productDto);
	}

	@Override
	public void delete(Long id) {
		this.productRepository.delete(this.find(id));
	}

	private Product find(Long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NO_RECORDS_FOUND));
	}

	private ProductDto persist(ProductDto productDto) {
		return ProductDto.create(this.productRepository.save(Product.create(productDto)));
	}

}
