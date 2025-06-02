package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * DTO with full information about order category 
 */
@Data
public class OrderDto implements Serializable {

	private static final long serialVersionUID = -2905865574398030796L;
	
	/**
	 * Order identifier 
	 */
	private Long id;
	
	/**
	 * Order creation date
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	/**
	 * Customer name 
	 */
	private String customerName;
	
	/**
	 * Customer address
	 */
	private String address;
	
	/**
	 * Order status. Optional field
	 */
	private Boolean isFinished;
	
	/**
	 * Order cost. (Sum of all items)
	 */
	private BigDecimal cost;
	
	/**
	 * Order items. Information about product and count 
	 */
	private Collection<? extends OrderItemDto> items;
	
}
