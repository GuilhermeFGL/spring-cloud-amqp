package com.example.payment.model.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.example.payment.model.entity.Product;

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
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable {

	private static final long serialVersionUID = 2381588818700865098L;

	private Long id;
	private Integer quantity;

	public static ProductDto create(Product product) {
		return new ModelMapper().map(product, ProductDto.class);
	}

}