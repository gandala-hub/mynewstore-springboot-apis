package com.example.mynewstore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mynewstore.entities.Product;
import com.example.mynewstore.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        // Logic to retrieve all products
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        // Logic to retrieve a product by its ID
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

  
    public void createProduct(Product product) {
        // Logic to create a new product
        productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        // Logic to delete a product by its ID
        productRepository.deleteById(id);
    }

}
