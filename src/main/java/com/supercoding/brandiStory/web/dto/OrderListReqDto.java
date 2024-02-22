package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderListReqDto {
    @Schema(description = "주문 ID", example = "1", required = true)
    private Integer ordersId;

    @Schema(description = "사용자 ID", example = "100", required = true)
    private Integer usersId;

    @Schema(description = "장바구니 아이템 ID", example = "500", required = true)
    private Integer cartItemsId;

    @Schema(description = "고객 이름", example = "홍길동", required = true)
    private String customerName;

    @Schema(description = "고객 전화번호", example = "010-1234-5678", required = true)
    private String customerPhone;

    @Schema(description = "고객 이메일", example = "honggildong@example.com", required = true)
    private String customerEmail;

    @Schema(description = "배송 주소", example = "서울시 강남구 테헤란로 123", required = true)
    private String deliveryAddress;

    @Schema(description = "주문 날짜 및 시간", example = "2023-01-01T12:00:00", required = true)
    private LocalDateTime orderDate;
}
