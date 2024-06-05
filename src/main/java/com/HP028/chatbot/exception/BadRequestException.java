package com.HP028.chatbot.exception;

import com.HP028.chatbot.common.response.ApiFailStatus;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{

    public BadRequestException(ApiFailStatus apiFailStatus) {
        super(HttpStatus.BAD_REQUEST, apiFailStatus.getMessage());
    }

}
