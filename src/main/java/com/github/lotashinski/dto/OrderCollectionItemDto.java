package com.github.lotashinski.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderCollectionItemDto {

	private Long id;
	
	private LocalDateTime createdAt;
	
	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
}
