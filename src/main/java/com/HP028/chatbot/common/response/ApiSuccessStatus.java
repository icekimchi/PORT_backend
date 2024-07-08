package com.HP028.chatbot.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApiSuccessStatus {
    RETRIEVE_SUCCESS(HttpStatus.OK, "조회 성공"),
    DELETE_SUCCESS(HttpStatus.OK, "삭제 성공"),
    UPDATE_SUCCESS(HttpStatus.OK, "수정 성공"),
    CREATE_SUCCESS(HttpStatus.CREATED, "생성 성공"),
    SIGNUP_SUCCESS(HttpStatus.CREATED,"회원가입 성공"),
    SIGNIN_SUCCESS(HttpStatus.ACCEPTED, "로그인 성공"),
    CREATE_CHATROOM_SUCCESS(HttpStatus.CREATED, "채팅방 생성 성공"),
    MESSAGE_SEND_AND_RECEIVE_SUCCESS(HttpStatus.OK, "메시지 전송 및 수신 성공");

    private final HttpStatus httpStatus;
    private final String message;

}
