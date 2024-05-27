package com.HP028.chatbot.common.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Builder
@Data
public class ApiResponse<T> {

    private final int status;
    private final String message;
    private final T body;

    public static <T> ResponseEntity<ApiResponse<T>> success(ApiSuccessStatus status, T data) {
        return ResponseEntity.status(status.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(status.getHttpStatus().value())
                        .message(status.getMessage())
                        .body(data)
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> fail(ApiFailStatus status){
        return ResponseEntity.status(status.getHttpStatus())
                .body(ApiResponse.<T>builder()
                        .status(status.getHttpStatus().value())
                        .build());
    }

}
