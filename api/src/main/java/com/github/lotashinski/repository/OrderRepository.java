package com.github.lotashinski.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
