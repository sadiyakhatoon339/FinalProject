package com.example.ECommerce.repo;

import com.example.ECommerce.entities.order.OrderProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
