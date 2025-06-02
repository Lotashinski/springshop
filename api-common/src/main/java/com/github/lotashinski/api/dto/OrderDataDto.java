package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

/**
 * DTO to save or update order
 */
@Data
public class OrderDataDto implements Serializable {

	private static final long serialVersionUID = -7282195516482010L;

	/**
	 * Customer name. Mandatory field
	 */
	private String customerName;
	
	/**
	 * Customer address. Mandatory field
	 */
	private String address;
	
	/**
	 * Order status. Optional field. The order is considered completed only if the value is {@code true}
	 */
	private Boolean isFinished;
	
	/**
	 * Order products collection (Product identifier and count). Mandatory field
	 */
	private Collection<? extends OrderItemDataDto> items;
	
}
