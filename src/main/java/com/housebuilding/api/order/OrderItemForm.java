package com.housebuilding.api.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemForm {
    @NotNull
    private Long materialId;
    private int quantity = 1;
}
