package com.github.lotashinski.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO with information about exceptions
 */
@Data
@AllArgsConstructor
public class ErrorDto {

	/**
	 * System code
	 */
	private String code;
	
	/**
	 * Text description
	 */
	private String message;
	
	/**
	 * Optional details
	 */
	private List<String> details;

}
