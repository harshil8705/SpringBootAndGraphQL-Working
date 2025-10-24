package com.springBoot.GraphQL.controller;

import com.springBoot.GraphQL.dto.OrderInput;
import com.springBoot.GraphQL.entity.Order;
import com.springBoot.GraphQL.service.OrderServiceImpl;
import com.springBoot.GraphQL.util.OrderBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final OrderBuilder orderBuilder;

    @MutationMapping
    public Order addNewOrderByUserId(@Argument Long userId, @Argument("input") OrderInput input) {

        Order order = orderBuilder.buildOrderFromOrderInput(input);

        return orderService.addNewOrderByUserId(userId, order);

    }

    @QueryMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();

    }

    @QueryMapping
    public List<Order> getAllOrdersByUserId(@Argument Long userId) {

        return orderService.getAllOrdersByUserId(userId);

    }

    @MutationMapping
    public Order updateOrderByOrderId(@Argument Long orderId, @Argument("input") OrderInput input) {

        Order order = orderBuilder.buildOrderFromOrderInput(input);

        return orderService.updateOrderByOrderId(orderId, order);

    }

    @MutationMapping
    public String deleteOrderByOrderId(@Argument Long orderId) {

        return orderService.deleteOrderByOrderId(orderId);

    }

}
