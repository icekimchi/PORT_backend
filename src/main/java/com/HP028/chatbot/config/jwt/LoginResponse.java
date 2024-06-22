package com.HP028.chatbot.config.jwt;

import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.common.response.ApiSuccessStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginResponse {
    private int status;
    private String message;
    private String email;

    public LoginResponse(ApiSuccessStatus apiSuccessStatus, String username) {
        this.status = apiSuccessStatus.getHttpStatus().value();
        this.message = apiSuccessStatus.getMessage();
        this.email = username;
    }

    public LoginResponse(HttpStatus httpStatus, ApiFailStatus apiFailStatus) {
        this.status = httpStatus.value();
        this.message = apiFailStatus.getMessage();
    }
}
