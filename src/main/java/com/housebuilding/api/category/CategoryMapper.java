package com.housebuilding.api.category;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "parentCategoryId", target = "parent.categoryId")
    @Mapping(target = "categoryId", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    @Mapping(source = "parent.categoryId", target = "parentCategoryId")
    CategoryDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "parentCategoryId", target = "parent.categoryId")
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);

    List<CategoryDto> toDtos(List<Category> all);
}