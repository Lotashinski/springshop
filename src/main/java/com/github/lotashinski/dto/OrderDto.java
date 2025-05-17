package com.github.lotashinski.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import lombok.Data;

@Data
public class OrderDto {

	private Long id;
	
	private LocalDateTime createdAt;
	
	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
	private Collection<? extends OrderItemDto> items;
	
}
