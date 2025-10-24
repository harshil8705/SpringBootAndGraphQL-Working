package com.springBoot.GraphQL.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderDetails;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_fk_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
