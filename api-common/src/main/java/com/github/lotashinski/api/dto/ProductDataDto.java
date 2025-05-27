package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;

@Data
public class ProductDataDto implements Serializable {

	private static final long serialVersionUID = 7816071223038363016L;

	private String title;
	
	private BigDecimal cost;
	
	private Collection<Long> categories;
	
}
