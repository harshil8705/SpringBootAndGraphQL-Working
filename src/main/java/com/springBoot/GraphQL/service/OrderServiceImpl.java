package com.springBoot.GraphQL.service;

import com.springBoot.GraphQL.entity.Order;
import com.springBoot.GraphQL.entity.User;
import com.springBoot.GraphQL.repository.OrderRepository;
import com.springBoot.GraphQL.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Order addNewOrderByUserId(Long userId, Order order) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId: " + userId + " doesn't exists."));

        Order newOrder = Order.builder()
                .orderDetails(order.getOrderDetails())
                .address(order.getAddress())
                .price(order.getPrice())
                .user(user)
                .build();
        user.getOrders().add(newOrder);

        return orderRepository.save(newOrder);

    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();

    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId: " + userId + " doesn't exists."));

        return orderRepository.findByUser(user);

    }

    @Override
    public Order updateOrderByOrderId(Long orderId, Order order) {

        Order oldOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with orderId: " + orderId + " doesn't exists."));

        oldOrder.setOrderDetails(order.getOrderDetails());
        oldOrder.setPrice(order.getPrice());
        oldOrder.setAddress(order.getAddress());

        return orderRepository.save(oldOrder);

    }

    @Override
    public String deleteOrderByOrderId(Long orderId) {

        Order orderToDelete = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with orderId: " + orderId + " doesn't exists."));

        orderRepository.delete(orderToDelete);

        return "Order with orderId: " + orderId + " removed Successfully.";

    }

}
