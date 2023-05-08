package com.housebuilding.api.order.paymentdetail;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDetailMapper {
    PaymentDetail toEntity(PaymentDetailDto paymentDetailDto);

    PaymentDetailDto toDto(PaymentDetail paymentDetail);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentDetail partialUpdate(PaymentDetailDto paymentDetailDto, @MappingTarget PaymentDetail paymentDetail);
}