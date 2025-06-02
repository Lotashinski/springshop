package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * DTO for displaying products collection item
 */
@Data
public class ProductCollectionItemDto implements Serializable {

	private static final long serialVersionUID = -3076146136267209835L;

	/**
	 * Product identifier 
	 */
	private Long id; 
	
	/**
	 * Product title
	 */
	private String title;
	
	/**
	 * Product cost
	 */
	private BigDecimal cost;
	
}
