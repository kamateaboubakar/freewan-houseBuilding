package com.housebuilding.api.order;

import com.housebuilding.api.common.BasicService;

import java.util.List;
import java.util.UUID;

public interface OrderService extends BasicService<Order, UUID> {
    List<Order> findAllByCustomerAccountId(String customerAccountId);
}
