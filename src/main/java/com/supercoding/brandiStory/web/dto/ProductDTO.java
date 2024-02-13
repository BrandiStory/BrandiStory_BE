package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDTO {
        @Schema(description = "제품ID", example = "1")
        private Integer productId;
        @Schema(description = "제품명", example = "버튼가디건")
        private String productName;
        @Schema(description = "가격", example = "20000")
        private Integer price;
        @Schema(description = "수량", example = "49")
        private Integer quantity;
        @Schema(description = "회사이름", example = "블링")
        private String companyName;
    }