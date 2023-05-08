package com.housebuilding.api.order;

import com.housebuilding.api.order.paymentdetail.PaymentDetailDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * A DTO for the {@link Order} entity
 */
@Data
public class OrderDto implements Serializable {
    private final UUID id;
    private final String customerAccountId;
    private final PaymentDetailDto paymentDetail;
    private final BigDecimal total;
    private final String deliveryAddressId;
    private final LocalDate deliveryDate;
    private final Date createdAt;
    private final OrderStatus status;
    private final Set<OrderItemDto> orderItems;
    private final BigDecimal totalOrderItem;
}