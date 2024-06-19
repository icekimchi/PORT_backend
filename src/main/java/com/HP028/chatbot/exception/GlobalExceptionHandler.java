package com.HP028.chatbot.exception;

import com.HP028.chatbot.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException e) {
        return ApiResponse.fail(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> allErrors = e.getFieldErrors();
        String message = allErrors.stream().map(error -> String.format("%s: %s, ", error.getField(), error.getDefaultMessage())).collect(Collectors.joining());
        if (message.endsWith(", ")) {
            message = message.substring(0, message.length() - 2);
        }
        return ApiResponse.fail(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponse> handleSQLException(SQLException e) {
        return ApiResponse.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        log.warn("Exception", e);
        return ApiResponse.fail(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
