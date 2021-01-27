package com.example.crud.model.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.example.crud.model.entity.Product;

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

	private static final long serialVersionUID = 2938272643682548375L;

	private Long id;
	private String name;
	private Integer quantity;
	private Double price;

	public static ProductDto create(Product product) {
		return new ModelMapper().map(product, ProductDto.class);
	}
}