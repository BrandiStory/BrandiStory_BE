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

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
}
