package com.supercoding.brandiStory.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class ProductDTO {
        @Schema(description = "제품ID", example = "1")
        private Integer id;
        @Schema(description = "제품명", example = "버튼가디건")
        private String productName;
        @Schema(description = "가격", example = "20000")
        private Integer price;
        @Schema(description = "수량", example = "49")
        private Integer quantity;
        @Schema(description = "회사이름", example = "블링")
        private String companyName;
        @Schema(description = "Time", example = "작성시간")
        private LocalDateTime createdAt;
    }