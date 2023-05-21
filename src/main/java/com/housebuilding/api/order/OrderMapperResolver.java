package com.housebuilding.api.order;

import com.housebuilding.api.client.account.AccountClient;
import com.housebuilding.api.client.account.AccountResponse;
import com.housebuilding.api.client.account.AddressResponse;
import lombok.AllArgsConstructor;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderMapperResolver {
    private final AccountClient accountClient;

    @ObjectFactory
    OrderDto resolve(Order order) {
        OrderDto dto = new OrderDto();
        AccountResponse customer = new AccountResponse();
        if (order.getCustomerAccountId() != null) {
            try {
                customer = accountClient.getAccountById(order.getCustomerAccountId());
            } catch (Exception e) {
                customer.setAccountId(UUID.fromString(order.getCustomerAccountId()));
            }
        }
        dto.setCustomer(customer);

        AddressResponse address = new AddressResponse();
        if (order.getDeliveryAddressId() != null) {
            try {
                address = accountClient.getAccountAddressById(Long.valueOf(order.getDeliveryAddressId()));
            } catch (NumberFormatException e) {
                address.setId(Long.valueOf(order.getDeliveryAddressId()));
            }

        }
        dto.setDeliveryAddress(address);
        return dto;
    }
}
