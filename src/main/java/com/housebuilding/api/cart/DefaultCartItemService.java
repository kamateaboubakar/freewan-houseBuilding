package com.housebuilding.api.cart;

import com.housebuilding.api.common.BaseServiceImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCartItemService extends BaseServiceImp<CartItem, Long, CartItemRepository> implements CartItemService {
    private final CartItemRepository cartItemRepository;

    public DefaultCartItemService(CartItemRepository repository) {
        super(repository);
        this.cartItemRepository = repository;
    }

    @Override
    public List<CartItem> findAllByCustomerAccountId(String customerAccountId) {
        return cartItemRepository.findAllByCustomerAccountId(customerAccountId);
    }

    @Override
    public CartItem findByCustomerAccountIdAndMaterialId(String customerAccountId, Long materialId) {
        return cartItemRepository.findByCustomerAccountIdAndMaterial_MaterialId(customerAccountId, materialId);
    }

    @Override
    public CartItem updateQuantityById(Long id, int qte) {
        CartItem cartItem = findById(id);
        cartItem.setQuantity(qte);
        return save(cartItem);
    }

    @Override
    public void deleteAllByCustomerAccountId(String customerAccountId) {
        cartItemRepository.deleteAllByCustomerAccountId(customerAccountId);
    }
}
