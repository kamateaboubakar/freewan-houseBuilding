package com.housebuilding.api.brand;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Brand} entity
 */
@Data
public final class BrandDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private  Long brandId;
    private  String label;
}