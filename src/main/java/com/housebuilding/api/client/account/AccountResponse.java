package com.housebuilding.api.client.account;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountResponse {
    private UUID accountId;
    private String email;
    private String firstName;
    private String lastName;
}
