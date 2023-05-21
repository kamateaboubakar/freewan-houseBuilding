package com.housebuilding.api.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link Material} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private  Long materialId;
    private  Long categoryId;
    private  Long brandId;
    private  UUID supplierId;
    private  String name;
    private  String description;
    private  BigDecimal price;
    private  Long unitId;
    private  String imageUrl;
}