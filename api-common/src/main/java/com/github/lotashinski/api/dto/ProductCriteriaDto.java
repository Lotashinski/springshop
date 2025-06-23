package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

/**
 * DTO for API requests for products selection 
 */
@Data
public class ProductCriteriaDto implements Serializable {
	
	private static final long serialVersionUID = 7516931820997851306L;

	/**
	 * Select only products with given IDs. The condition does not apply if the
	 * collection is empty or {@code null}.
	 */
	private Collection<? extends Long> ids;
	
	/**
	 * Select only products in category. The condition does not apply if the
	 * value is {@code null}.
	 */
	private Long category;
	
	/**
	 * Select only products in categories. The condition does not apply if the
	 * collection is empty or {@code null}.
	 */
	private Collection<? extends Long> categories;
	
}
