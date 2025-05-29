package com.github.lotashinski.ui.client;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;

import feign.RequestLine;

public interface OrderClient {

	@RequestLine("POST /orders")
	OrderDto create(@RequestBody OrderDataDto data);
	
}
