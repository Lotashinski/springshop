package com.github.lotashinski.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCollectionItemDto {

	private Long id; 
	
	private String title;
	
	private BigDecimal cost;
	
}
