package com.HP028.chatbot.config.jwt;

import com.HP028.chatbot.member.domain.Member;
import lombok.Getter;

@Getter
public class CustomUserInfo {

    private String email;
    private String password;
    private String role;

    public CustomUserInfo(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static CustomUserInfo of(Member member) {
        return new CustomUserInfo(member.getEmail(), member.getPassword(), member.getRole().toString());
    }
}
