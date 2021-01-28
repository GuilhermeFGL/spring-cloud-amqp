package com.example.payment.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.example.payment.model.dto.SellDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sell")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sell implements Serializable {

	private static final long serialVersionUID = 7941763390118968225L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate date;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sell", cascade = CascadeType.REFRESH)
	private List<ProductSell> productSells;

	@Column(nullable = false, length = 10)
	private Double total;

	public static Sell create(SellDto sellDto) {
		return new ModelMapper().map(sellDto, Sell.class);
	}

}
