package com.github.lotashinski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
