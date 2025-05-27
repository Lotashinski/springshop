package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;

@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = -8802990014614086481L;

	private Long id; 
	
	private String title;
	
	private BigDecimal cost;
	
	private Collection<? extends CategoryCollectionItemDto> categories;
	
}
