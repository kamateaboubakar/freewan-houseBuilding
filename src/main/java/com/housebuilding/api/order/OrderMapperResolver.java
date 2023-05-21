package com.housebuilding.api.order;

import com.housebuilding.api.client.account.AccountClient;
import com.housebuilding.api.client.account.AccountResponse;
import com.housebuilding.api.client.account.AddressResponse;
import com.housebuilding.api.order.paymentdetail.PaymentDetailDto;
import com.housebuilding.api.order.paymentdetail.PaymentDetailMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderMapperResolver {
    private final AccountClient accountClient;
    private final PaymentDetailMapper paymentDetailMapper;
    private final OrderItemMapper orderItemMapper;

    @ObjectFactory
    OrderDto resolve(Order order) {
        if (order == null) {
            return null;
        }

        Set<OrderItemDto> orderItems;
        UUID id;
        AccountResponse customer = new AccountResponse();
        if (order.getCustomerAccountId() != null) {
            try {
                customer = accountClient.getAccountById(order.getCustomerAccountId());
            } catch (Exception e) {
                customer.setAccountId(UUID.fromString(order.getCustomerAccountId()));
            }
        }
        PaymentDetailDto paymentDetail;
        BigDecimal total;
        AddressResponse address = new AddressResponse();
        if (order.getDeliveryAddressId() != null) {
            try {
                address = accountClient.getAccountAddressById(Long.valueOf(order.getDeliveryAddressId()));
            } catch (NumberFormatException e) {
                address.setId(Long.valueOf(order.getDeliveryAddressId()));
            }

        }
        LocalDate deliveryDate;
        Date createdAt;
        OrderStatus status;
        BigDecimal totalOrderItem;

        orderItems = orderItemSetToOrderItemDtoSet(order.getOrderItems());
        id = order.getId();
        paymentDetail = paymentDetailMapper.toDto(order.getPaymentDetail());
        total = order.getTotal();
        deliveryDate = order.getDeliveryDate();
        createdAt = order.getCreatedAt();
        status = order.getStatus();
        totalOrderItem = order.getTotalOrderItem();

        return new OrderDto(id, customer, paymentDetail, total, address, deliveryDate, createdAt, status, orderItems, totalOrderItem);
    }

    protected Set<OrderItemDto> orderItemSetToOrderItemDtoSet(Set<OrderItem> set) {
        if (set == null) {
            return Collections.emptySet();
        }

        Set<OrderItemDto> set1 = new LinkedHashSet<OrderItemDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (OrderItem orderItem : set) {
            set1.add(orderItemMapper.toDto(orderItem));
        }

        return set1;
    }
}
