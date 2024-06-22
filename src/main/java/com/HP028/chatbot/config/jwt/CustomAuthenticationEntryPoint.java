package com.HP028.chatbot.config.jwt;

import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.common.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "UNAUTHORIZATION_EXCEPTION_HANDLER")
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException{
        log.error("Not Authenticated Request", authException);
//        setResponseMessage(response, ApiFailStatus.INVALID_TOKEN);

    }

    public void setResponseMessage(HttpServletResponse response, ApiFailStatus status) throws IOException {
        log.error("setResponse");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse apiResponse = ApiResponse.fail(status.getMessage(), HttpStatus.valueOf(response.getStatus()));
        response.getWriter().println(objectMapper.writeValueAsString(apiResponse));
    }
}
