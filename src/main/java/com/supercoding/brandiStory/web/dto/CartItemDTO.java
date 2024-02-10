package com.supercoding.brandiStory.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class CartItemDTO {
    @Schema(description = "장바구니ID", example = "장바구니1")
    private Integer Id;
    private Integer userId;
    //제품명은 왜 Integer로 설정해주셨지?
    private Integer productName;
    private Integer quantity;
    private Integer totalPrice;
    private LocalDateTime createdAt;
}
