package com.housebuilding.api.material;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * A DTO for the {@link Material} entity
 */
public record MaterialDto(Long materialId, Long categoryId, UUID supplierId, String name,
                          String description, BigDecimal price, Long unitId) implements Serializable {
}