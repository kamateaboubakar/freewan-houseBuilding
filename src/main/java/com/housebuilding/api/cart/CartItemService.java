package com.housebuilding.api.cart;

import com.housebuilding.api.common.BasicService;

import java.util.List;

public interface CartItemService extends BasicService<CartItem, Long> {
    List<CartItem> findAllByCustomerAccountId(String customerAccountId);

    CartItem findByCustomerAccountIdAndMaterialId(String customerAccountId, Long materialId);

    CartItem updateQuantityById(Long id, int qte);

    void deleteAllByCustomerAccountId(String customerAccountId);
}
