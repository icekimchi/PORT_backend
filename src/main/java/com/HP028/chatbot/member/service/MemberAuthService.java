package com.HP028.chatbot.member.service;

import com.HP028.chatbot.exception.BadRequestException;
import com.HP028.chatbot.member.domain.Member;
import com.HP028.chatbot.member.dto.MemberSignUpResponse;
import com.HP028.chatbot.member.dto.MemberSignUpRequest;
import com.HP028.chatbot.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.HP028.chatbot.common.response.ApiFailStatus.*;

@Service
@RequiredArgsConstructor
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signUp(MemberSignUpRequest request) {

        if (memberRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException(DUPLICATED_MEMBER_FIELD);
        }

        Member member = Member.createMember(request);
        member.encodePassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return modelMapper.map(savedMember, MemberSignUpResponse.class);
    }
}
