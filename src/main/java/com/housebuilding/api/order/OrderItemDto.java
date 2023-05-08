package com.housebuilding.api.order;

import com.housebuilding.api.unit.UnitDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A DTO for the {@link OrderItem} entity
 */
@Data
public class OrderItemDto implements Serializable {
    private final Long id;
    private final Long materialMaterialId;
    private final String materialName;
    private final String materialDescription;
    private final BigDecimal materialPrice;
    private final UnitDto materialUnit;
    private final int quantity;
    private final Date createdAt;
    private final BigDecimal total;
}