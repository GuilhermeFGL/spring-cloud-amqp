package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.entity.ProductSell;

@Repository
public interface ProductSellRepository extends JpaRepository<ProductSell, Long> {

}
