package com.example.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.model.dto.SellDto;
import com.example.payment.service.SellService;

@RestController
@RequestMapping("/sell")
public class SellController {

	private SellService sellService;
	private PagedResourcesAssembler<SellDto> assembler;

	@Autowired
	public SellController(SellService sellService, PagedResourcesAssembler<SellDto> assembler) {
		this.sellService = sellService;
		this.assembler = assembler;
	}

	@GetMapping("/{id}")
	public SellDto findById(@PathVariable("id") Long id) {
		SellDto sellDto = this.sellService.findById(id);
		sellDto.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SellController.class).findById(id)).withSelfRel());
		return sellDto;
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "ASC") Direction sortDirection) {
		Page<SellDto> sells = this.sellService.findAll(PageRequest.of(page, limit, Sort.by(sortDirection, "date")));
		sells.stream().forEach(p -> p.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SellController.class).findById(p.getId())).withSelfRel()));

		return new ResponseEntity<>(this.assembler.toModel(sells), HttpStatus.OK);
	}

	@PostMapping
	public SellDto create(@RequestBody SellDto sellDto) {
		SellDto productDto = this.sellService.create(sellDto);
		productDto.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SellController.class).findById(productDto.getId())).withSelfRel());
		return productDto;
	}
}
