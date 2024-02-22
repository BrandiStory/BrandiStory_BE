package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartItemBody {
    @Schema(description = "유저ID", example = "1")
    private Integer usersId;
    @Schema(description = "제품ID", example = "3")
    private Integer productId;
    @Schema(description = "가격", example = "30000")
    private Integer price;
    @Schema(description = "수량", example = "4")
    private Integer quantity;
}
