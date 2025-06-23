package com.github.lotashinski.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderCriteriaDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;
import com.github.lotashinski.api.entity.Order;
import com.github.lotashinski.api.exceptions.ResourceNotFoundException;
import com.github.lotashinski.api.mapper.OrderMapper;
import com.github.lotashinski.api.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrderMapper orderMapper;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	
	@SuppressWarnings("unchecked")
	@Test
    void findByCriteria_ShouldReturnListOfProductItems() {
    	OrderCriteriaDto criteria = new OrderCriteriaDto();
    	
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> entities = List.of(order1, order2);
        
        OrderCollectionItemDto itemDto1 = new OrderCollectionItemDto();
        OrderCollectionItemDto itemDto2 = new OrderCollectionItemDto();
        List<OrderCollectionItemDto> expectedDtos = List.of(itemDto1, itemDto2);

        Mockito.when(orderRepository.findAll(Mockito.any(Specification.class), Mockito.any(Sort.class)))
                .thenReturn(entities);
        Mockito.when(orderMapper.toItemDtoList(entities))
                .thenReturn(expectedDtos);
        
        List<? extends OrderCollectionItemDto> result = orderService.findByCriteria(criteria);

        Assertions.assertEquals(expectedDtos, result);
        Mockito.verify(orderRepository).findAll(Mockito.any(Specification.class), Mockito.eq(Sort.by("createdAt").descending()));
        Mockito.verify(orderMapper).toItemDtoList(Mockito.anyList());
    }
    
    @Test
    void create() {
    	OrderDataDto data = new OrderDataDto();
    	Order order = new Order();
    	OrderDto dto = new OrderDto();
    	
    	Mockito.when(orderMapper.toEntity(Mockito.any(OrderDataDto.class), Mockito.any(Order.class)))
    		.thenReturn(order);
    	Mockito.when(orderRepository.save(Mockito.any(Order.class)))
    		.thenReturn(order);
    	Mockito.when(orderMapper.toDto(Mockito.any(Order.class)))
    		.thenReturn(dto);
    	
    	OrderDto result = orderService.create(data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(orderMapper).toEntity(data, order);
    	Mockito.verify(orderRepository).save(order);
    	Mockito.verify(orderMapper).toDto(order);
    }
    
    @Test
    void update_ifExists() {
    	OrderDataDto data = new OrderDataDto();
    	Order order = new Order();
    	OrderDto dto = new OrderDto();
    	Long identifier = 1L;
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(order));
    	Mockito.when(orderMapper.toEntity(Mockito.any(OrderDataDto.class), Mockito.any(Order.class)))
			.thenReturn(order);
    	Mockito.when(orderMapper.toDto(Mockito.any(Order.class)))
    		.thenReturn(dto);
    	
    	OrderDto result = orderService.update(identifier, data);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(orderRepository).save(order);
    	Mockito.verify(orderMapper).toDto(order);
    }
    
    @Test
    void update_ifNotExists() {
    	OrderDataDto data = new OrderDataDto();
    	Long identifier = 1L;
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> orderService.update(identifier, data));
    	Mockito.verify(orderRepository).findById(identifier);
    }
    
    @Test
    void read_ifExists() {
    	Long identifier = 1L;
    	Order order = new Order();
    	OrderDto dto = new OrderDto();
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(order));
    	Mockito.when(orderMapper.toDto(Mockito.any(Order.class)))
    		.thenReturn(dto);
    	
    	OrderDto result = orderService.read(identifier);
    	
    	Assertions.assertEquals(result, dto);
    	Mockito.verify(orderRepository).findById(identifier);
    	Mockito.verify(orderMapper).toDto(order);
    }
    
    @Test
    void read_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> orderService.read(identifier));
    	
    	Mockito.verify(orderRepository).findById(identifier);
    }
    
    @Test
    void delete_ifExists() {
    	Long identifier = 1L;
    	Order order = new Order();
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.of(order));
    	
    	orderService.delete(identifier);
    	
    	Mockito.verify(orderRepository).findById(identifier);
    	Mockito.verify(orderRepository).delete(order);
    }
    
    @Test
    void delete_ifNotExists() {
    	Long identifier = 1L;
    	
    	Mockito.when(orderRepository.findById(Mockito.anyLong()))
    		.thenReturn(Optional.empty());
    	
    	Assertions.assertThrows(ResourceNotFoundException.class, () -> orderService.delete(identifier));
    	
    	Mockito.verify(orderRepository).findById(identifier);
    }

}
