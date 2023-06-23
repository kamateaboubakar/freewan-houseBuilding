package com.housebuilding.api.cart;

import com.housebuilding.api.client.account.AccountService;
import com.housebuilding.api.material.Material;
import com.housebuilding.api.material.MaterialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.housebuilding.api.Route.CART;
import static com.housebuilding.api.Route.ROOT;
import static com.housebuilding.api.Route.V1_URI;

@RestController
@RequestMapping(ROOT + V1_URI + CART)
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final MaterialService materialService;
    private final AccountService accountService;

    @GetMapping("")
    public List<CartItemDto> getAllCartItemsByCustomerAccountId(@RequestParam @NotBlank String customerAccountId) {
        return cartItemMapper.toDtos(cartItemService.findAllByCustomerAccountId(customerAccountId));
    }

    @GetMapping("/{id}")
    public CartItemDto findCartItemById(@PathVariable Long id) {
        return cartItemMapper.toDto(cartItemService.findById(id));
    }


    @PostMapping("")
    public CartItemDto addItemToCart(@RequestBody @Valid CartItemForm form) {
        Material material = materialService.findById(form.getMaterialId());
        accountService.verifyCustomerAccountId(form.getCustomerAccountId());
        CartItem cartItem = cartItemService.findByCustomerAccountIdAndMaterialId(form.getCustomerAccountId(),
                form.getMaterialId());
        if (cartItem == null) {
            cartItem = cartItemMapper.toEntity(form);
            cartItem.setMaterial(material);
        } else {
            cartItem.addQuantity(form.getQuantity());
        }

        return cartItemMapper.toDto(cartItemService.save(cartItem));
    }

    @PostMapping("/{id}/quantity/{qte}")
    public CartItemDto updateQuantityByCartItemId(@PathVariable Long id, @Min(1) @PathVariable int qte) {
        return cartItemMapper.toDto(cartItemService.updateQuantityById(id, qte));
    }

    @DeleteMapping("/{id}")
    public void deleteCartItemById(@PathVariable Long id) {
        cartItemService.deleteById(id);
    }

    @DeleteMapping("")
    @Transactional
    public void deleteAllCartItemByCustomerAccountId(@NotBlank @RequestParam String customerAccountId) {
        cartItemService.deleteAllByCustomerAccountId(customerAccountId);
    }
}
