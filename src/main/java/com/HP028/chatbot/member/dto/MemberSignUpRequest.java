package com.HP028.chatbot.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignUpRequest {

    private String email;

    private String name;

    private String password;
}
