package com.HP028.chatbot.member.controller;

import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import com.HP028.chatbot.member.dto.MemberSignUpResponse;
import com.HP028.chatbot.member.service.MemberAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.HP028.chatbot.common.response.ApiSuccessStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member/auth")
public class MemberAuthController {

    private final MemberAuthService memberAuthService;

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<MemberSignUpResponse>> signUp(@RequestBody MemberSignUpRequest request) {
        MemberSignUpResponse response= memberAuthService.signUp(request);
        return ApiResponse.success(SIGNUP_SUCCESS,response);
    }
}
