package com.github.lotashinski.api.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.api.entity.Order;
import com.github.lotashinski.api.mapper.decorator.OrderMapperDecorator;
import com.github.lotashinski.api.dto.OrderCollectionItemDto;
import com.github.lotashinski.api.dto.OrderDataDto;
import com.github.lotashinski.api.dto.OrderDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapper.class})
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {

	OrderDto toDto(Order entity);
	
	OrderCollectionItemDto toItemDto(Order entity);
	
	List<OrderCollectionItemDto> toItemDtoList(List<? extends Order> entities);
	
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "items", ignore = true)
	Order toEntity(OrderDataDto dto, @MappingTarget Order entity);
	
}
