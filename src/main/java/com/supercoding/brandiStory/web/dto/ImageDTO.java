package com.supercoding.brandiStory.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ImageDTO {
    @Schema(description = "이미지ID", example = "1")
    private Integer id;
    @Schema(description = "제품ID", example = "제품아이디:1")
    private Integer productId;
    @Schema(description = "이미지", example = "이미지1,이미지2,이미지3")
    private Integer image;
    @Schema(description = "이미지순서", example = "이미지순서123")
    private Integer sequence;
}
