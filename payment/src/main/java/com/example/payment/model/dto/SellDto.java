package com.example.payment.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.example.payment.model.entity.Sell;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class SellDto extends RepresentationModel<SellDto> implements Serializable {

	private static final long serialVersionUID = -7334701345685781346L;

	private Long id;
	private Double total;
	private List<ProductSellDto> productSells;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	public static SellDto create(Sell sell) {
		return new ModelMapper().map(sell, SellDto.class);
	}
}