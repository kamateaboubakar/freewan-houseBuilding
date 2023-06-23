package com.housebuilding.api.cart;

import com.housebuilding.api.unit.UnitDto;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link CartItem}
 */
@Value
public class CartItemDto implements Serializable {
    Long id;
    Long materialId;
    UUID materialSupplierId;
    String materialSupplierName;
    boolean materialSupplierVerified;
    boolean materialSupplierOfficial;
    String materialName;
    BigDecimal materialPrice;
    UnitDto materialUnit;
    String materialImageUrl;
    String customerAccountId;
    int quantity;
    BigDecimal total;
}