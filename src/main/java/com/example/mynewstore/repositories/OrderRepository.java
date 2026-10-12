package com.example.mynewstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mynewstore.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
