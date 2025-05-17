package com.github.lotashinski.dto;

import lombok.Data;

@Data
public class OrderItemCreateDto {

	private Long productId;
	
	private Integer count;
	
}
