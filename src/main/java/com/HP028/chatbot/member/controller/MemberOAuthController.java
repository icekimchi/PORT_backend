package com.HP028.chatbot.member.controller;


import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.member.domain.Provider;
import com.HP028.chatbot.member.dto.MemberOAuthAccessRequest;
import com.HP028.chatbot.member.dto.MemberOAuthAccessResponse;
import com.HP028.chatbot.member.service.MemberOAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.HP028.chatbot.common.response.ApiSuccessStatus.SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member/oauth")
@Tag(name = "소셜 회원가입 API", description = "소셜 회원가입, 로그인")
public class MemberOAuthController {
    private final MemberOAuthService memberOAuthService;
    @PostMapping("/{provider}")
    public ResponseEntity<ApiResponse<MemberOAuthAccessResponse>> signUp(@RequestBody @Valid MemberOAuthAccessRequest request, @PathVariable Provider provider) throws JsonProcessingException {
        return ApiResponse.success(SIGNUP_SUCCESS, memberOAuthService.OAuthSignUp(request, provider));
    }

}