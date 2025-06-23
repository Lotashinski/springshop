package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Information about new order item
 */
@Data
public class OrderItemDataDto implements Serializable {

	private static final long serialVersionUID = 4561700418949710157L;

	/**
	 * Product identifier
	 */
	private Long productId;
	
	/**
	 * Products count
	 */
	private Integer count;
	
}
