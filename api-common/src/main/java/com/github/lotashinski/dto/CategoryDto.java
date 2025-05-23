package com.github.lotashinski.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = -1655161514231980058L;

	private Long id;
	
	private String title;
	
}
