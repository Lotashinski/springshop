package com.github.lotashinski.dto;

import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;

@Data
public class ProductDto {

	private Long id; 
	
	private String title;
	
	private BigDecimal price;
	
	private Collection<Long> categories;
	
}
