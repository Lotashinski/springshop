package com.github.lotashinski.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;

@Data
public class OrderDataDto implements Serializable {

	private static final long serialVersionUID = -7282195516482010L;

	private String customerName;
	
	private String address;
	
	private Boolean isFinished;
	
	private Collection<? extends OrderItemDataDto> items;
	
}
