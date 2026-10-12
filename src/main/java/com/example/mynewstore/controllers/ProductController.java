package com.example.mynewstore.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mynewstore.entities.Product;
import com.example.mynewstore.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController 
@RequestMapping ("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public List<Product> getProducts() {
        // Logic to retrieve all products
        return productService.getProducts(); // Replace with actual implementation
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // Logic to retrieve a product by its ID
        return productService.getProductById(id); // Replace with actual implementation
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody Product product) {
        // Logic to create a new product
        productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        // Logic to update an existing product
        return productService.updateProduct(id, product); // Replace with actual implementation
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // Logic to delete a product by its ID
        productService.deleteProduct(id);
    }
     
}
