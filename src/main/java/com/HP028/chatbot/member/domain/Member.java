package com.HP028.chatbot.member.domain;

import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
//@Builder
@RequiredArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Member(String email, String name, String password, RoleType roleType) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roleType = roleType;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public static Member createMember(MemberSignUpRequest request) {
        return new Member(request.getEmail(), request.getName(), request.getPassword(), RoleType.USER);
    }

}
