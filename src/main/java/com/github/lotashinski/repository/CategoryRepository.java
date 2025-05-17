package com.github.lotashinski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
