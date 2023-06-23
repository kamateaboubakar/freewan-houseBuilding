package com.housebuilding.api.cart;

import com.housebuilding.api.unit.UnitMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UnitMapper.class}, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {

    @Mapping(target = "materialImageUrl", source = "material.imageUrl")
    @Mapping(target = "materialPrice", source = "material.price")
    @Mapping(target = "materialName", source = "material.name")
    @Mapping(target = "materialSupplierOfficial", source = "material.supplier.official")
    @Mapping(target = "materialSupplierVerified", source = "material.supplier.verified")
    @Mapping(target = "materialSupplierName", source = "material.supplier.name")
    @Mapping(target = "materialSupplierId", source = "material.supplier.supplierId")
    @Mapping(target = "materialId", source = "material.materialId")
    @Mapping(target = "materialUnit", source = "material.unit")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "material.materialId", source = "materialId")
    CartItem toEntity(CartItemForm form);

    List<CartItemDto> toDtos(List<CartItem> cartItems);
}