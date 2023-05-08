package com.housebuilding.api.order.paymentdetail;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link PaymentDetail} entity
 */
@Data
public class PaymentDetailDto implements Serializable {
    private final UUID id;
    private final String provider;
    private final String reference;
    private final String amount;
    private final double paymentFees;
    private final double deliveryFees;
    private final PaymentStatus status;
    private final Date createdAt;
    private final Date updatedAt;
}