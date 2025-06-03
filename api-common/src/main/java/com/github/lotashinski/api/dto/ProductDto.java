package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;

/**
 * DTO with full information about the product 
 */
@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = -8802990014614086481L;

	/**
	 * Product identifier
	 */
	private Long id; 
	
	/**
	 * Product title
	 */
	private String title;
	
	/**
	 * Product title
	 */
	private BigDecimal cost;
	
	/**
	 * Collection with linked categories
	 */
	private Collection<? extends CategoryCollectionItemDto> categories;
	
}
