package com.supercoding.brandiStory.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class OrdersEntity {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cart_items_id", nullable = false)
    private CartItemEntity cartItemsId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhone;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private LocalDateTime orderDate;
}
