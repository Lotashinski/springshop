package com.github.lotashinski.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

	private String code;
	
	private String message;
	
	private List<String> details;

}
