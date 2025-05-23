package com.github.lotashinski.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.dto.OrderItemDataDto;
import com.github.lotashinski.dto.OrderItemDto;
import com.github.lotashinski.entity.OrderItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

	@Mapping(target = "productId", source = "product.id")
	@Mapping(target = "productTitle", source = "product.title")
	OrderItemDto toDto(OrderItem entity);
	
	@Mapping(target = "costOfOne", ignore = true)
	OrderItem toEntity(OrderItemDataDto dto, @MappingTarget OrderItem entity);
	
}
