package com.housebuilding.api.order;

import com.housebuilding.api.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(name = Route.ORDER)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

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
    public OrderDto addNewOrder(@RequestBody OrderDto materialDto) {
        Order material = orderMapper.toEntity(materialDto);
        return orderMapper.toDto(orderService.save(material));
    }

    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable String orderId) {
        orderService.deleteById(UUID.fromString(orderId));
    }
}
