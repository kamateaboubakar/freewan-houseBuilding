package com.housebuilding.api.unit;

import java.io.Serializable;

/**
 * A DTO for the {@link Unit} entity
 */
public record UnitDto(Long unitId, String code, String label) implements Serializable {
}