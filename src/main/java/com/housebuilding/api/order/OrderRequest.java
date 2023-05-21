package com.housebuilding.api.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class OrderRequest {
    @NotBlank
    private String customerAccountId;
    @NotBlank
    private String deliveryAddressId;
    private Set<@Valid OrderItemForm> orderItems;
}
