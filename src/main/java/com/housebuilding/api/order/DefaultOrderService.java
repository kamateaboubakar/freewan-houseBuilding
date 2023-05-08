package com.housebuilding.api.order;

import com.housebuilding.api.common.BaseServiceImp;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultOrderService extends BaseServiceImp<Order, UUID, OrderRepository> implements OrderService {
    private final OrderRepository orderRepository;
    public DefaultOrderService(OrderRepository repository) {
        super(repository);
        this.orderRepository = repository;
    }

    @Override
    public List<Order> findAllByCustomerAccountId(String customerAccountId) {
        return orderRepository.findAllByCustomerAccountId(customerAccountId);
    }
}
