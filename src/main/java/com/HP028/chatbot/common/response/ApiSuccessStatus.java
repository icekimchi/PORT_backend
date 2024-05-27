package com.HP028.chatbot.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiSuccessStatus {
    SIGNUP_SUCCESS(HttpStatus.CREATED,"회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.ACCEPTED, "로그인 성공");

    private final HttpStatus httpStatus;
    private final String message;

}
