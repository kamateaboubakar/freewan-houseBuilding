package com.housebuilding.api.order;

import com.housebuilding.api.client.account.AccountResponse;
import com.housebuilding.api.client.account.AddressResponse;
import com.housebuilding.api.order.paymentdetail.PaymentDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
    private  UUID id;
    private  AccountResponse customer;
    private  PaymentDetailDto paymentDetail;
    private  BigDecimal total;
    private  AddressResponse deliveryAddress;
    private  LocalDate deliveryDate;
    private  Date createdAt;
    private  OrderStatus status;
    private Set<OrderItemDto> orderItems;
    private BigDecimal totalOrderItem;
}