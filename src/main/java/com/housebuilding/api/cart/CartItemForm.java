package com.housebuilding.api.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemForm {
    @NotNull
    @Schema(description = "Material identifiant.")
    private Long materialId;
    @Schema(description = "Quantity.")
    @Min(1)
    private int quantity = 1;
    @NotBlank
    @Schema(description = "Customer bank account id.")
    private String customerAccountId;
}
