package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * Information about order item
 */
@Data
public class OrderItemDto implements Serializable {

	private static final long serialVersionUID = -5812310603180973443L;

	/**
	 * Product identifier
	 */
	private Long productId;
	
	/**
	 * Product title
	 */
	private String productTitle;
	
	/**
	 * Cost of the product at the time of order
	 */
	private BigDecimal costOfOne;

	/**
	 * Products count
	 */
	private Integer count;
	
}
