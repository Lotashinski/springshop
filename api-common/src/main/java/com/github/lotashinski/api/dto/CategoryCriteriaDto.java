package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class CategoryCriteriaDto implements Serializable {
	
	private static final long serialVersionUID = 124709630937067310L;

	private Collection<? extends Long> ids;
	
	private Long product;
	
}
