package com.housebuilding.api.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private Long categoryId;
    private Long parentCategoryId;
    private String label;
    private String description;
    private String imageUrl;
}