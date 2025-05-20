package com.github.lotashinski.mapper.decorator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.lotashinski.dto.OrderDataDto;
import com.github.lotashinski.entity.Order;
import com.github.lotashinski.entity.Product;
import com.github.lotashinski.exceptions.ResourceNotFoundException;
import com.github.lotashinski.mapper.OrderMapper;
import com.github.lotashinski.repository.ProductRepository;

import lombok.Setter;

public abstract class OrderMapperDecorator implements OrderMapper {
	
	@Autowired
	@Setter
	private ProductRepository productRepository;
	
	@Autowired
	@Setter
	@Qualifier("delegate")
	private OrderMapper delegate;
	
	public Order toEntity(OrderDataDto dto, Order entity) {
		final Order e = delegate.toEntity(dto, entity);
		
		e.getItems()
			.clear();
		
		Collection<Long> productIds = dto.getItems()
				.stream()
				.map(i -> i.getProductId())
				.collect(Collectors.toSet());
		
		Collection<Product> products = new HashSet<>(productRepository.findAllById(productIds));
		if (productIds.size() != products.size()) {
			Collection<Long> cotainsIds = products.stream()
					.map(Product::getId)
					.toList();
			productIds.removeAll(cotainsIds);
			
			throw new ResourceNotFoundException(String.format("Products not found: %s", productIds.toString()));
		}
		
		Map<Long, Integer> productsCountMap = dto.getItems()
			.stream()
			.collect(Collectors.toMap(i -> i.getProductId(), i -> i.getCount()));
		
		products
			.stream()
			.forEach(p -> e.putItem(p, productsCountMap.get(p.getId())));
		
		return e;
	}
	
}
