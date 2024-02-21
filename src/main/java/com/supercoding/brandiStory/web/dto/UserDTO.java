package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.supercoding.brandiStory.repository.entity.enums.SexType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {
    @Schema(description = "유저아이디")
    private Integer userId;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "패스워드")
    private String password;

    @Schema(description = "유저이름")
    private String name;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "성별")
    private SexType gender;

    @Schema(description = "계정생성 일자")
    private LocalDateTime createdAt;

}
