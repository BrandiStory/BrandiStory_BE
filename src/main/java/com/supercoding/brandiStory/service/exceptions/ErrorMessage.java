package com.supercoding.brandiStory.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ErrorMessage {
    WRONG_TYPE_TOKEN("손상된 토큰입니다"),
    EXPIRED_TOKEN("만료된 토큰입니다"),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰입니다"),
    SIGNATURE_FAIL_TOKEN("시그니처 검증에 실패한 토큰입니다"),
    UNKNOWN_ERROR("기타 에러");

    private String errMsg;

    public String getErrMsg() {
        return this.errMsg;
    }
}
