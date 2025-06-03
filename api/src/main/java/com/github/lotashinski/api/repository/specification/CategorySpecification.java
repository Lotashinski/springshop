package com.github.lotashinski.api.repository.specification;

import java.util.Collection;

import org.springframework.data.jpa.domain.Specification;

import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.entity.Product;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class CategorySpecification {
	
	public static Specification<Category> hasIdentifiersIn(Collection<? extends Long> ids) {
		return (root, query, criteriaBuilder) -> {
			if(ids == null || ids.isEmpty()) return criteriaBuilder.conjunction();
			
			return root.get("id").in(ids);
		};
	}
	
	public static Specification<Category> hasExistsProduct(Long id) {
		return (root, query, criteriaBuilder) -> {
			if (id == null ) return criteriaBuilder.conjunction();
			
			Join<Category, Product> categoruJoin = root.join("products", JoinType.INNER);
			return criteriaBuilder.equal(categoruJoin.get("id"), id);
		};
	}
	
}
