package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

        @Schema(description = "이미지리스트", example = "이미지리스트")
        private List<ImageDTO> imageList;
}

