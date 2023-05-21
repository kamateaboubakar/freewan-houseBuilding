package com.housebuilding.api.material;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {
    @Mapping(target = "materialId", ignore = true)
    @Mapping(source = "unitId", target = "unit.unitId")
    @Mapping(source = "brandId", target = "brand.brandId")
    @Mapping(source = "supplierId", target = "supplier.supplierId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    Material toEntity(MaterialDto materialDto);

    @InheritInverseConfiguration(name = "toEntity")
    MaterialDto toDto(Material material);

    List<MaterialDto> toDtos(List<Material> materials);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Material partialUpdate(MaterialDto materialDto, @MappingTarget Material material);
}