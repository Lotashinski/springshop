package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * DTO for displaying Order collection item
 */
@Data
public class OrderCollectionItemDto implements Serializable {

	private static final long serialVersionUID = 1480226178084224256L;

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
	 * Customer name. 
	 */
	private String customerName;
	
	/**
	 * Customer address
	 */
	private String address;
	
	/**
	 * Order status
	 */
	private Boolean isFinished;
	
}
