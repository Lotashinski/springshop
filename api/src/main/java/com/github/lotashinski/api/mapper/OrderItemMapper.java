package com.github.lotashinski.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.api.entity.OrderItem;
import com.github.lotashinski.api.dto.OrderItemDataDto;
import com.github.lotashinski.api.dto.OrderItemDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

	@Mapping(target = "productId", source = "product.id")
	@Mapping(target = "productTitle", source = "product.title")
	OrderItemDto toDto(OrderItem entity);
	
	@Mapping(target = "costOfOne", ignore = true)
	OrderItem toEntity(OrderItemDataDto dto, @MappingTarget OrderItem entity);
	
}
