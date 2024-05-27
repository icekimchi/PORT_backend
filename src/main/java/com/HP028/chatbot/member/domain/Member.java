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

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public static Member createMember(MemberSignUpRequest request) {
        Member member = new Member();
        member.email = request.getEmail();
        member.name = request.getName();
        member.password = request.getPassword();
        member.roleType = RoleType.USER;
        return member;
    }

}
