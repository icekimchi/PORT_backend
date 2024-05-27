package com.HP028.chatbot.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiFailStatus {
    INVALID_ID_OR_PASSWORD(HttpStatus.BAD_REQUEST, "아이디 비밀번호 오류"),
    INVALID_TOKEN_PREFIX(HttpStatus.BAD_REQUEST, "토큰 접두사 오류"),
    DUPLICATED_MEMBER_FIELD(HttpStatus.BAD_REQUEST, "중복된 이메일 혹은 이름입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
