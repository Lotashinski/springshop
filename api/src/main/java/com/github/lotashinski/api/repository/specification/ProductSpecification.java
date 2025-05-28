package com.github.lotashinski.api.repository.specification;

import java.util.Collection;

import org.springframework.data.jpa.domain.Specification;

import com.github.lotashinski.api.entity.Category;
import com.github.lotashinski.api.entity.Product;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class ProductSpecification {

	public static Specification<Product> hasIdentifiersIn(Collection<? extends Long> ids) {
		return (root, query, criteriaBuilder) -> {
			if(ids == null || ids.isEmpty()) return criteriaBuilder.conjunction();
			
			return root.get("id").in(ids);
		};
	}
	
	public static Specification<Product> hasExistsInCategories(Collection<? extends Long> ids) {
		return (root, query, criteriaBuilder) -> {
			if(ids == null || ids.isEmpty()) return criteriaBuilder.conjunction();
			
			Subquery<Long> subquery = query.subquery(Long.class);
	        Root<Product> product = subquery.from(Product.class);
	        
	        Join<Product, Category> categoruJoin = product.join("categories", JoinType.INNER);
	        
	        subquery.select(product.get("id"))
	               .where(criteriaBuilder.and(
	            		   criteriaBuilder.equal(product.get("id"), root.get("id")),
	            		   categoruJoin.get("id").in(ids)
	               ));

	        return criteriaBuilder.exists(subquery);
		};
	}
	
	public static Specification<Product> hasExistsInCategory(Long id) {
		return (root, query, criteriaBuilder) -> {
			if (id == null ) return criteriaBuilder.conjunction();
			
			Join<Product, Category> categoruJoin = root.join("categories", JoinType.INNER);
			return criteriaBuilder.equal(categoruJoin.get("id"), id);
		};
	}
	
}
