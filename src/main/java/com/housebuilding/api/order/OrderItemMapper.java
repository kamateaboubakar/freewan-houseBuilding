package com.housebuilding.api.order;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    @Mapping(source = "materialPrice", target = "material.price")
    @Mapping(source = "materialDescription", target = "material.description")
    @Mapping(source = "materialName", target = "material.name")
    @Mapping(source = "materialId", target = "material.materialId")
    OrderItem toEntity(OrderItemDto orderItemDto);

    @Mapping(source = "materialId", target = "material.materialId")
    OrderItem toEntity(OrderItemForm form);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "materialPrice", source = "material.price")
    @Mapping(target = "materialDescription", source = "material.description")
    @Mapping(target = "materialName", source = "material.name")
    @Mapping(target = "materialUnit", source = "material.unit")
    OrderItemDto toDto(OrderItem orderItem);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem partialUpdate(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);
}