package com.github.lotashinski.ui.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;

import feign.Headers;
import feign.QueryMap;
import feign.RequestLine;

public interface OrderClient {

	@RequestLine("POST /orders")
	@Headers("Content-Type: application/json")
	OrderDto create(@RequestBody OrderDataDto data);
	
	@RequestLine("GET /orders")
	List<? extends OrderCollectionItemDto> findByCriteria(@QueryMap  OrderCriteriaDto criteria);
	
}
