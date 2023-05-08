package com.housebuilding.api.brand;

import java.io.Serializable;

/**
 * A DTO for the {@link Brand} entity
 */
public record BrandDto(Long brandId, String label) implements Serializable {
}