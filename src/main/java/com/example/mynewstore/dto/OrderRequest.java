package com.example.mynewstore.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class OrderRequest {
    @NotBlank (message = "Customer name is required")
    private String customerName;
    @NotBlank (message = "Customer email is required")
    @Email (message = "Invalid email format")
    private String customerEmail;
    

    //@NotBlank (message = "Order items are required")
    @Valid 
    @NotEmpty (message = "Order must contain at least one item")
    private List<OrderItemRequest> orderItems;
    

}
