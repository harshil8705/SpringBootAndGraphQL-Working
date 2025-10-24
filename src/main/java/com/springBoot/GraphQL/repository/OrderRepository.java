package com.springBoot.GraphQL.repository;

import com.springBoot.GraphQL.entity.Order;
import com.springBoot.GraphQL.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

}
