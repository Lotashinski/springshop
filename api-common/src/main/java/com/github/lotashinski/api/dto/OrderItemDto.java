package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemDto implements Serializable {

	private static final long serialVersionUID = -5812310603180973443L;

	private Long productId;
	
	private String productTitle;
	
	private BigDecimal costOfOne;

	private Integer count;
	
}
