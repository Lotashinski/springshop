package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO with full information about the category
 */
@Data
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = -1655161514231980058L;

	/**
	 * Category identifier
	 */
	private Long id;
	
	/**
	 * Category title
	 */
	private String title;
	
}
