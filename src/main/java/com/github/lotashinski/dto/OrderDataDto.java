package com.github.lotashinski.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class OrderDataDto {

	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
	private Collection<? extends OrderItemCreateDto> items;
	
}
