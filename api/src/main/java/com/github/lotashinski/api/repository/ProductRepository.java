package com.github.lotashinski.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.lotashinski.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	@Override
	@Query("select p from Product p where p.id = :id and (p.isDeleted is null or not p.isDeleted)")
	Optional<Product> findById(@Param("id") Long id);

}
