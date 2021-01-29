package com.example.payment.model.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.example.payment.model.entity.ProductSell;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSellDto extends RepresentationModel<ProductSellDto> implements Serializable {

	private static final long serialVersionUID = 3277044024726131411L;

	private Long id;
	private Integer quantity;

	public static ProductSellDto create(ProductSell productSell) {
		return new ModelMapper().map(productSell, ProductSellDto.class);
	}

}