package com.HP028.chatbot.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    private int status;
    private String message;
    private LocalDateTime now;
}
