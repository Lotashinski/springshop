package com.github.lotashinski.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderDto {

	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
	private Collection<? extends OrderItemDto> items;
	
}
