package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductCollectionItemDto implements Serializable {

	private static final long serialVersionUID = -3076146136267209835L;

	private Long id; 
	
	private String title;
	
	private BigDecimal cost;
	
}
