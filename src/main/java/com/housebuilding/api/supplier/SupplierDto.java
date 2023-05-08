package com.housebuilding.api.supplier;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link Supplier} entity
 */
public record SupplierDto(UUID supplierId, String name, String basicInfo, String address, String contact,
                          boolean verified, boolean official) implements Serializable {
}