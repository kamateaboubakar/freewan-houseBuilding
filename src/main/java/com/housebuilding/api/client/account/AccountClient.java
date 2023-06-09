package com.housebuilding.api.client.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "accountClient", url = "${account.host.url}", configuration = AccountClientConfig.class)
public interface AccountClient {
    @GetMapping("/admin/api/v1/accounts/{accountId}")
    AccountResponse getAccountById(@PathVariable String accountId);

    @GetMapping("/admin/api/v1/addresses/{addressId}")
    AddressResponse getAccountAddressById(@PathVariable Long addressId);
}
