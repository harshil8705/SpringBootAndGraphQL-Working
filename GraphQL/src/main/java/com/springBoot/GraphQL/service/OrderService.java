package com.springBoot.GraphQL.service;

import com.springBoot.GraphQL.entity.Order;

import java.util.List;

public interface OrderService {

    Order addNewOrderByUserId(Long userId, Order order);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(Long userId);

    Order updateOrderByOrderId(Long orderId, Order order);

    String deleteOrderByOrderId(Long orderId);

}
