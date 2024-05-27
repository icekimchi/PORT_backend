package com.HP028.chatbot.member.service;

import com.HP028.chatbot.exception.member.DuplicatedMemberFieldException;
import com.HP028.chatbot.member.domain.Member;
import com.HP028.chatbot.member.dto.MemberSignInRequest;
import com.HP028.chatbot.member.dto.MemberSignInResponse;
import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import com.HP028.chatbot.member.dto.MemberSignUpResponse;
import com.HP028.chatbot.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.HP028.chatbot.common.response.ApiFailStatus.DUPLICATED_MEMBER_FIELD;

@Service
@RequiredArgsConstructor
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signUp(MemberSignUpRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())){
            throw new DuplicatedMemberFieldException(DUPLICATED_MEMBER_FIELD);
        }
        Member member = Member.createMember(request);
        member.encodePassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);
        return modelMapper.map(savedMember, MemberSignUpResponse.class);
    }

//    public MemberSignInResponse signIn(MemberSignInRequest request) {
//        Member member = memberRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
//        if (!member.getPassword().equals(request.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//        return MemberSignInResponse.of(member);
//    }
}
