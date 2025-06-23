package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;

/**
 * DTO to save or update product
 */
@Data
public class ProductDataDto implements Serializable {

	private static final long serialVersionUID = 7816071223038363016L;

	/**
	 * Product title. Mandatory field
	 */
	private String title;
	
	/**
	 * Product cost. Mandatory field
	 */
	private BigDecimal cost;
	
	/**
	 * Identifier for link to categories 
	 */
	private Collection<Long> categories;
	
}
