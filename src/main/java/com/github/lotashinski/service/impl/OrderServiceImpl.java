package com.github.lotashinski.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lotashinski.dto.OrderCollectionItemDto;
import com.github.lotashinski.dto.OrderCriteriaDto;
import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.dto.OrderDto;
import com.github.lotashinski.entity.Order;
import com.github.lotashinski.mapper.OrderMapper;
import com.github.lotashinski.repository.OrderRepository;
import com.github.lotashinski.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final OrderMapper orderMapper;
	
	@Transactional(readOnly = true)
	@Override
	public List<? extends OrderCollectionItemDto> findByCriteria(OrderCriteriaDto criteria) {
		log.info("Load orderss by criteria [criteria: {}]", criteria);
		
		// TODO change to query
		List<Order> entities = orderRepository.findAll(Sort.by("createdAt").descending());

		return orderMapper.toItemDtoList(entities);
	}

	@Transactional
	@Override
	public OrderDto create(OrderDataDto dto) {
		log.info("Create new order [data: {}]", dto);
		
		Order entity = orderMapper.toEntity(dto, new Order());
		orderRepository.save(entity);

		return orderMapper.toDto(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public OrderDto read(Long id) {
		log.info("Read order by id [id: {}]", id);
		
		Order entity = getEntityOrThrow(id);
		
		return orderMapper.toDto(entity);
	}

	@Transactional
	@Override
	public OrderDto update(Long id, OrderDataDto data) {
		log.info("Update order by id [id: {}, data: {}]", id, data);
		
		Order entity = getEntityOrThrow(id);
		orderMapper.toEntity(data, entity);
		orderRepository.save(entity);
		
		return orderMapper.toDto(entity);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		log.info("Delete order by id [id: {}]", id);
		
		Order entity = getEntityOrThrow(id);
		orderRepository.delete(entity);
	}
	
	private Order getEntityOrThrow(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> ExceptionUtills.createNotFounException(Order.class, id));
	}
	
}
