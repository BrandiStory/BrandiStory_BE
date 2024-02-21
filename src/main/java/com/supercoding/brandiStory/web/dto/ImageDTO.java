package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ImageDTO {
    @Schema(description = "이미지ID", example = "1")
    private Integer imageId;
    @Schema(description = "제품ID", example = "제품아이디:1")
    private Integer productId;
    @Schema(description = "이미지", example = "이미지1,이미지2,이미지3")
    private String image;
    @Schema(description = "이미지순서", example = "이미지순서123")
    private Integer sequence;
}
