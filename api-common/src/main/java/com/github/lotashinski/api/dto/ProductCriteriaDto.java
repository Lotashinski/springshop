package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class ProductCriteriaDto implements Serializable {
	
	private static final long serialVersionUID = 7516931820997851306L;

	private Collection<? extends Long> ids;
	
	private Long category;
	
	private Collection<? extends Long> categories;
	
}
