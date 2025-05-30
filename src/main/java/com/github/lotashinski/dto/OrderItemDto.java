package com.github.lotashinski.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto {

	private Long productId;
	
	private String productTitle;
	
	private BigDecimal costOfOne;

	private Integer count;
	
}
