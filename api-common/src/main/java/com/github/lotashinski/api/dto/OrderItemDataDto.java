package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemDataDto implements Serializable {

	private static final long serialVersionUID = 4561700418949710157L;

	private Long productId;
	
	private Integer count;
	
}
