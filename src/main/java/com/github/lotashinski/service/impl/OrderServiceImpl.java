package com.github.lotashinski.service.impl;

import org.springframework.stereotype.Service;

import com.github.lotashinski.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl {

	private final OrderRepository orderRepository;
	
	
}
