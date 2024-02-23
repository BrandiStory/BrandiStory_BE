package com.supercoding.brandiStory.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderListReqDto {
    private Integer orders_id;
    private Integer cart_items_id;
    private String customerEmail;
    private String customerName;
    private Integer customerPhone;
    private String deliveryAddress;
    private LocalDateTime orderDate;
    private String productName;
    private Integer quantity;
    private Integer total_price;
    private Integer users_id;

}
