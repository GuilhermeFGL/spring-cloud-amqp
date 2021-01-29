package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payment.model.entity.Sell;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

}
