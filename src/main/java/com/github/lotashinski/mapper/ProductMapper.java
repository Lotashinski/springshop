package com.github.lotashinski.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.lotashinski.dto.ProductCollectionItemDto;
import com.github.lotashinski.dto.ProductDataDto;
import com.github.lotashinski.dto.ProductDto;
import com.github.lotashinski.entity.Product;
import com.github.lotashinski.mapper.decorator.ProductMapperDecorator;

@Mapper
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

	ProductDto toDto(Product entity);
	
	ProductCollectionItemDto toItemDto(Product entity);
	
	List<ProductCollectionItemDto> toItemDtoList(List<? extends Product> entities);
	
	@Mapping(target = "categories", ignore = true)
	Product toEntity(ProductDataDto dto, @MappingTarget Product entity);
	
}
