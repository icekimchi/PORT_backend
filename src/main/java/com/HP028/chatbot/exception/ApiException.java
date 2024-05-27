package com.HP028.chatbot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class ApiException extends RuntimeException {

    HttpStatus httpStatus;
    String message;

    protected ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
