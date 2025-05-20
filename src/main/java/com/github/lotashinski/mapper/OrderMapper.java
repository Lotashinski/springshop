package com.github.lotashinski.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.dto.OrderCollectionItemDto;
import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.dto.OrderDto;
import com.github.lotashinski.entity.Order;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapper.class})
public interface OrderMapper {

	OrderDto toDto(Order entity);
	
	OrderCollectionItemDto toItemDto(Order entity);
	
	List<OrderCollectionItemDto> toItemDtoList(List<? extends Order> entities);
	
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "items", ignore = true)
	Order toEntity(OrderDataDto dto, @MappingTarget Order entity);
	
}
