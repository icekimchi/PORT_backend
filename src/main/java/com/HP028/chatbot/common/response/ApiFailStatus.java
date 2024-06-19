package com.HP028.chatbot.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiFailStatus {

    //400 BAD_REQUEST
    INVALID_PASSWORD("비밀번호 오류"),
    INVALID_TOKEN_PREFIX("토큰 접두사 오류"),
    DUPLICATED_MEMBER_FIELD("중복된 이메일 혹은 이름입니다"),

    //401 UNAUTHORIZED

    //403 FORBIDDEN

    //404 NOT_FOUND
    USER_NOT_FOUND("사용자를 찾을 수 없습니다");
    private final String message;
}
