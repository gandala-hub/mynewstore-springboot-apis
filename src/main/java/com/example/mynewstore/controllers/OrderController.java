package com.example.mynewstore.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mynewstore.dto.OrderRequest;
import com.example.mynewstore.entities.Order;
import com.example.mynewstore.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/api/orders")
@RequiredArgsConstructor 
public class OrderController {

    private final OrderService orderService;

@PostMapping
public Order createOrder(@Valid @RequestBody OrderRequest orderRequest) {
    return orderService.createOrder(orderRequest);
}

//Get all orders
// public List<Order> getAllOrders() {
//     return orderService.getAllOrders();
// }
//Get order by id
// public Order getOrderById(Long id) {
//     return orderService.getOrderById(id);
// }

}
