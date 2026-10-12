package com.example.mynewstore.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "orders")
@Getter 
@Setter 
@AllArgsConstructor
@NoArgsConstructor 
@Builder 
public class Order {
    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column (name = "customer_name", nullable = false)
    private String customerName;

    @Column (name = "customer_email", nullable = false)
    private String customerEmail;

    @Column (nullable = false)
    private String status;

    @Column (name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist 
    protected void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @JsonManagedReference 
    @OneToMany(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

}
