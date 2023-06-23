package com.housebuilding.api.client.account;

import com.housebuilding.api.exception.ApplicationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountClient accountClient;

    public void verifyCustomerAccountId(String customerAccountId) {
        try {
            accountClient.getAccountById(customerAccountId);
        } catch (FeignException.NotFound e) {
            throw new ApplicationException("No account found for customer account id '%s'".formatted(customerAccountId));
        }
    }
}
