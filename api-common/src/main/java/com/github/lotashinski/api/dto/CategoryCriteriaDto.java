package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

/**
 * DTO for API requests for category selection
 */
@Data
public class CategoryCriteriaDto implements Serializable {

	private static final long serialVersionUID = 124709630937067310L;

	/**
	 * Select only categories with given IDs. The condition does not apply if the
	 * collection is empty or {@code null}.
	 */
	private Collection<? extends Long> ids;

	/**
	 * A selection of categories that are linked to a given product. The condition does not apply if the
	 * value is {@code null}.
	 */ 
	private Long product;

}
