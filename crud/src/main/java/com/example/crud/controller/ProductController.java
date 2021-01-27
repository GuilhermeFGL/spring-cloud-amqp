package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.dto.ProductDto;
import com.example.crud.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;
	private PagedResourcesAssembler<ProductDto> assembler;

	@Autowired
	public ProductController(ProductService productService, PagedResourcesAssembler<ProductDto> assembler) {
		this.productService = productService;
		this.assembler = assembler;
	}
	
	@GetMapping(value = "/{id}")
	public ProductDto findById(@PathVariable("id") Long id) {
		ProductDto productDto = productService.findById(id);
		productDto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(id))
				.withSelfRel());
		
		return productDto;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "ASC") Direction sortDirection) {
		Page<ProductDto> products = productService.findAll(PageRequest.of(page, limit, Sort.by(sortDirection, "name")));

		products.forEach(p -> p.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(p.getId())).withSelfRel()));

		return new ResponseEntity<>(assembler.toModel(products), HttpStatus.OK);
	}

	@PostMapping
	public ProductDto create(@RequestBody ProductDto requestDto) {
		ProductDto productDto = productService.create(requestDto);
		productDto.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(productDto.getId()))
				.withSelfRel());
		
		return productDto;
	}

	@PutMapping
	public ProductDto update(@RequestBody ProductDto requestDto) {
		ProductDto productDto = productService.update(requestDto);
		productDto.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).findById(requestDto.getId()))
				.withSelfRel());
	
		return productDto;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		productService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
}