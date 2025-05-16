package com.github.lotashinski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
