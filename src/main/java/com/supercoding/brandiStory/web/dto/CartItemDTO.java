package com.supercoding.brandiStory.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartItemDTO {
    @Schema(description = "장바구니ID", example = "장바구니1")
    private Integer Id;
    private Integer userId;
    //제품명은 왜 Integer로 설정해주셨지?
    private Integer productName;
    private Integer productId; //product ID 추가! DB에도 반영해주기
    private Integer price; //개별가격 추가! DB에도 반영해주기
    private Integer quantity;
    private Integer totalPrice;
  //  private LocalDateTime createdAt;
//이거 DB이름 바꿔야될 것 같음.
//    public Object getProductId() {
//    }
}
