package com.HP028.chatbot.exception;

import com.HP028.chatbot.common.response.ApiFailStatus;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(ApiFailStatus apiFailStatus) {
        super(HttpStatus.NOT_FOUND, apiFailStatus.getMessage());
    }
}
