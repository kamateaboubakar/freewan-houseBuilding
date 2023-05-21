package com.housebuilding.api.order;

import com.housebuilding.api.order.paymentdetail.PaymentDetailMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PaymentDetailMapper.class, OrderItemMapper.class, OrderMapperResolver.class})
public interface OrderMapper {
    OrderDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderDto orderDto, @MappingTarget Order order);

    @AfterMapping
    default void linkOrderItems(@MappingTarget Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
    }

    List<OrderDto> toDtos(List<Order> all);

    Order toEntity(OrderRequest request);
}