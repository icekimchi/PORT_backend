package com.HP028.chatbot.member.domain;

import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 10)
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "owner")
    private List<ChatRoom> chatRooms = new ArrayList<>();

    public void encodePassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public static Member createMember(MemberSignUpRequest request) {
        Member member = new Member();
        member.email = request.getEmail();
        member.name = request.getName();
        member.password = request.getPassword();
        member.role = RoleType.USER;
        member.provider = Provider.EMAIL;
        return member;
    }

    public static Member createOAuthMember(OAuthUserInfo request,Provider provider) {
        Member member = new Member();
        member.email = request.getEmail();
        member.name = request.getName();
        member.role = RoleType.USER;
        member.provider = provider;
        return member;
    }
}
