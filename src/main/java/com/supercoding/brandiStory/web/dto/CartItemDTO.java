package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartItemDTO {
    @Schema(description = "장바구니ID", example = "5")
    private Integer cartItemId;
    @Schema(description = "유저ID", example = "1")
    private Integer userId;
    @Schema(description = "제품ID", example = "3")
    private Integer productId;
    @Schema(description = "가격", example = "30000")
    private Integer price;
    @Schema(description = "수량", example = "4")
    private Integer quantity;
    @Schema(description = "총합계", example = "30000(개별가격)*4(수량)=120000(총합계)")
    private Integer totalPrice;
}
