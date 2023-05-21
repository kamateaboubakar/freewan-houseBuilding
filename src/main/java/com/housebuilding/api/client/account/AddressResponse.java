package com.housebuilding.api.client.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private  Long id;
    private  UUID accountId;
    private  String label;
    private Double longitude;
    private Double latitude;
}
