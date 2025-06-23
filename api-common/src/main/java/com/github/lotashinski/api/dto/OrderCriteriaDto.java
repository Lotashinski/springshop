package com.github.lotashinski.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO for API requests for order selection
 */
@Data
public class OrderCriteriaDto implements Serializable {
	
	private static final long serialVersionUID = 4369623311623826746L;
	
	/**
	 * Show only finished or not completed orders. The condition does not apply if the
	 * value is {@code null}.
	 */
	private Boolean isFinished;
	
}
