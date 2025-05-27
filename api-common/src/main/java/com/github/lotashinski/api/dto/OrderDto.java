package com.github.lotashinski.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class OrderDto implements Serializable {

	private static final long serialVersionUID = -2905865574398030796L;

	private Long id;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
	private Collection<? extends OrderItemDto> items;
	
}
