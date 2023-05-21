package com.housebuilding.api.order;

import com.housebuilding.api.common.http.ApiResponse;
import com.housebuilding.api.common.http.ApiResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderApiResponse extends ApiResponse {
    private String orderId;

    public OrderApiResponse(ApiResponseCode apiResponseCode, String message, String orderId) {
        super(apiResponseCode, message);
        this.orderId = orderId;
    }
}
