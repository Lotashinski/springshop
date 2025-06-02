package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO to save or update category
 */
@Data
public class CategoryDataDto implements Serializable {

	private static final long serialVersionUID = 7761162175522342187L;
	
	/**
	 * Category title. Mandatory field
	 */
	private String title;
	
}
