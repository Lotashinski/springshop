package com.github.lotashinski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
