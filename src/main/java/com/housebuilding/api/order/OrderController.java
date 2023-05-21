package com.housebuilding.api.order;

import com.housebuilding.api.common.http.ApiResponseCode;
import com.housebuilding.api.exception.ApplicationException;
import com.housebuilding.api.material.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.housebuilding.api.Route.ORDER;
import static com.housebuilding.api.Route.ROOT;
import static com.housebuilding.api.Route.V1_URI;

@RestController
@RequestMapping(ROOT + V1_URI + ORDER)
@RequiredArgsConstructor
@Transactional
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    private final MaterialService materialService;

    @GetMapping("")
    public List<OrderDto> findAllOrders() {
        return orderMapper.toDtos(orderService.findAll());
    }

    @GetMapping("customers/{customerAccountId}")
    public List<OrderDto> findAllCustomerOrdersByCustomerAccountId(@PathVariable String customerAccountId) {
        return orderMapper.toDtos(orderService.findAllByCustomerAccountId(customerAccountId));
    }

    @GetMapping("/{orderId}")
    public OrderDto findOrderById(@PathVariable String orderId) {
        return orderMapper.toDto(orderService.findById(UUID.fromString(orderId)));
    }


    @PostMapping("")
    public OrderApiResponse addNewOrder(@RequestBody OrderRequest request) {
        Order material = orderMapper.toEntity(request);
        material.getOrderItems().forEach(orderItem -> materialService.findById(orderItem.getMaterial().getMaterialId()));
        Order savedOrder = orderService.save(material);

        return new OrderApiResponse(ApiResponseCode.SUCCESS, "Order created with success", savedOrder.getId().toString());
    }

    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable String orderId) {
        Order order = orderService.findById(UUID.fromString(orderId));
        if (order.isPending()) {
            order.setStatus(OrderStatus.CANCELED);
            orderService.save(order);
        } else {
            throw new ApplicationException("Cannot cancel this order.");
        }
    }
}
