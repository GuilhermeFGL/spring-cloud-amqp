package com.example.payment.service.implemantation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.payment.exception.ResourceNotFoundException;
import com.example.payment.model.dto.SellDto;
import com.example.payment.model.entity.ProductSell;
import com.example.payment.model.entity.Sell;
import com.example.payment.repository.ProductSellRepository;
import com.example.payment.repository.SellRepository;
import com.example.payment.service.SellService;

@Component
public class SellServiceImpl implements SellService {

	private static final String MSG_NO_RECORDS_FOUND = "No records found for this ID";
	
	private SellRepository sellRepository;
	private ProductSellRepository productSellRepository;

	@Autowired
	public SellServiceImpl(SellRepository sellRepository, ProductSellRepository productSellRepository) {
		this.sellRepository = sellRepository;
		this.productSellRepository = productSellRepository;
	}

	@Override
	public SellDto create(SellDto sellDto) {
		Sell sell = this.sellRepository.save(Sell.create(sellDto));

		List<ProductSell> productSells = sellDto.getProductSells().stream().map(p -> {
			ProductSell productSell = ProductSell.create(p);
			productSell.setSell(sell);
			return this.productSellRepository.save(productSell);
		}).collect(Collectors.toList());
		sell.setProductSells(productSells);

		return SellDto.create(sell);
	}

	@Override
	public SellDto findById(Long id) {
		return SellDto.create(this.sellRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NO_RECORDS_FOUND)));
	}

	@Override
	public Page<SellDto> findAll(Pageable pageable) {
		return this.sellRepository.findAll(pageable).map(SellDto::create);
	}

}
