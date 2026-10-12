package com.example.mynewstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mynewstore.entities.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
