package com.HP028.chatbot.exception.auth;

import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.exception.ApiException;
public class TokenPrefixException extends ApiException {

    public TokenPrefixException(ApiFailStatus apiFailStatus) {
        super(apiFailStatus.getHttpStatus(), apiFailStatus.getMessage());
    }
}
