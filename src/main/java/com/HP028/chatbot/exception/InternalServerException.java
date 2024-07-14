package com.HP028.chatbot.exception;

import com.HP028.chatbot.common.response.ApiFailStatus;
import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiException {

    public InternalServerException(ApiFailStatus apiFailStatus) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, apiFailStatus.getMessage());
    }

}
