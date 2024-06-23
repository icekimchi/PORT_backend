package com.HP028.chatbot.member.domain;

import com.HP028.chatbot.chat.domain.ChatMessage;
import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

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
        return member;
    }

    public static Member createTempMember(String name, String password, RoleType role) {
        Member member = new Member();
        member.email = "tempemail@temp.com";
        member.name = name;
        member.password = password;
        member.role = role;
        return member;
    }

}
