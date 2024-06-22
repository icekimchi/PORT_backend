package com.HP028.chatbot.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiFailStatus {

    //400 BAD_REQUEST
    SIGN_IN_FAIL("로그인 실패"),
    INVALID_TOKEN_PREFIX("토큰 접두사 오류"),
    DUPLICATED_MEMBER_FIELD("중복된 이메일 혹은 이름입니다"),

    //401 UNAUTHORIZED
    INVALID_TOKEN("유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN("만료된 토큰입니다"),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰입니다"),
    EMPTY_CLAIMS("토큰 클레임이 비어있습니다"),

    //403 FORBIDDEN

    //404 NOT_FOUND
    USER_NOT_FOUND("사용자를 찾을 수 없습니다");
    private final String message;
}
