package com.HP028.chatbot.member.service;

import com.HP028.chatbot.config.jwt.JwtUtil;
import com.HP028.chatbot.member.domain.Member;
import com.HP028.chatbot.member.domain.OAuthUserInfo;
import com.HP028.chatbot.member.domain.Provider;
import com.HP028.chatbot.member.dto.MemberOAuthAccessRequest;
import com.HP028.chatbot.member.dto.MemberOAuthAccessResponse;
import com.HP028.chatbot.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberOAuthService {
    private final MemberRepository memberRepository;
    private final NaverOAuthClient naverOAuthClient;
    private final KakaoOAuthClient kakaoOAuthClient;
    private final JwtUtil jwtUtil;

    public MemberOAuthAccessResponse OAuthSignUp(MemberOAuthAccessRequest request, Provider provider) throws JsonProcessingException{
        OAuthUserInfo userInfo = getUserInfo(provider, request.getAccessToken());

        // 이미 가입된 회원인지 확인
        Optional<Member> existingMember = memberRepository.findByEmail(userInfo.getEmail());

        if (existingMember.isPresent()) {
            // 이미 가입된 회원이면 로그인 처리
            return new MemberOAuthAccessResponse(createAccessResponse(existingMember.get()));
        } else {
            Member member = Member.createOAuthMember(userInfo,provider);
            Member savedMember = memberRepository.save(member);
            return new MemberOAuthAccessResponse(createAccessResponse(savedMember));
        }
    }

    private OAuthUserInfo getUserInfo(Provider provider, String accessToken) throws JsonProcessingException {
        switch (provider) {
            case NAVER:
                return naverOAuthClient.getUserInfo(accessToken);
            case KAKAO:
                return kakaoOAuthClient.getUserInfo(accessToken);
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }

    private String createAccessResponse(Member member) {
        return jwtUtil.createJwt(member.getName(), member.getRole().toString());
    }

}