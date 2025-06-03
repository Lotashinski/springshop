package com.github.lotashinski.api.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.github.lotashinski.api.entity.Order;

public class OrderSpecification {

	public static Specification<Order> isFinished(Boolean isFinished) {
		return (root, query, criteriaBuilder) -> {
			if (isFinished == null) return criteriaBuilder.conjunction();
			
			return isFinished 
					? criteriaBuilder.isTrue(root.get("isFinished")) 
					: criteriaBuilder.or(criteriaBuilder.isFalse(root.get("isFinished")), 
							criteriaBuilder.isNull(root.get("isFinished")));
		};
	}
	
}
