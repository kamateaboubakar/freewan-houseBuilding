package com.housebuilding.api.category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
public record CategoryDto(Long categoryId, Long parentCategoryId, String label,
                          String description) implements Serializable {
}