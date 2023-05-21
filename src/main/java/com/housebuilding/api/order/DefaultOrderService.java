package com.housebuilding.api.order;

import com.housebuilding.api.client.account.AccountClient;
import com.housebuilding.api.common.BaseServiceImp;
import com.housebuilding.api.exception.ApplicationException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultOrderService extends BaseServiceImp<Order, UUID, OrderRepository> implements OrderService {
    private final OrderRepository orderRepository;
    private final AccountClient accountClient;

    public DefaultOrderService(OrderRepository repository, AccountClient accountClient) {
        super(repository);
        this.orderRepository = repository;
        this.accountClient = accountClient;
    }

    @Override
    public List<Order> findAllByCustomerAccountId(String customerAccountId) {
        return orderRepository.findAllByCustomerAccountId(customerAccountId);
    }

    @Override
    public Order save(Order entity) {
        // Just to verify if account and address exists in account ws.
        try {
            accountClient.getAccountById(entity.getCustomerAccountId());
            accountClient.getAccountAddressById(Long.valueOf(entity.getDeliveryAddressId()));
        } catch (FeignException.FeignClientException e) {
            throw new ApplicationException(e.getMessage());
        }
        return super.save(entity);
    }
}
