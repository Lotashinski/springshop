package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for displaying category collection item
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCollectionItemDto implements Serializable {

	private static final long serialVersionUID = 5126849896585110048L;

	/**
	 * Category identifier
	 */
	private Long id;
	
	/**
	 * Category title
	 */
	private String title;
	
}
