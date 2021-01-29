package com.example.payment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.payment.model.dto.SellDto;

@Service
public interface SellService {

	SellDto create(SellDto sellDto);

	SellDto findById(Long id);

	Page<SellDto> findAll(Pageable pageable);

}
