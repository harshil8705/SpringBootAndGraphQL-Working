package com.springBoot.GraphQL.util;

import com.springBoot.GraphQL.dto.OrderInput;
import com.springBoot.GraphQL.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderBuilder {

    public Order buildOrderFromOrderInput(OrderInput input) {

        return Order.builder()
                .orderDetails(input.getOrderDetails())
                .address(input.getAddress())
                .price(input.getPrice())
                .build();

    }

}
