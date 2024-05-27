package com.HP028.chatbot.exception.member;

import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.exception.ApiException;

public class DuplicatedMemberFieldException extends ApiException {

    public DuplicatedMemberFieldException(ApiFailStatus apiFailStatus) {
        super(apiFailStatus.getHttpStatus(), apiFailStatus.getMessage());
    }
}
