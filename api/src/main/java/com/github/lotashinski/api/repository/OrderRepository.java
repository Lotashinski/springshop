package com.github.lotashinski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.lotashinski.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
