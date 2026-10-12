package com.example.mynewstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mynewstore.entities.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {

}
