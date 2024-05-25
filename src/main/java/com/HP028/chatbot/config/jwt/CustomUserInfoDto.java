package com.HP028.chatbot.config.jwt;

import com.HP028.chatbot.member.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserInfoDto {
    private Long id;

    private String email;

    private String name;

    private String password;

    private RoleType role;
}
