package com.housebuilding.api.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCustomerAccountId(String customerBankAccount);

    CartItem findByCustomerAccountIdAndMaterial_MaterialId(String customerBankAccount, Long materialId);

    void deleteAllByCustomerAccountId(String customerAccountId);
}