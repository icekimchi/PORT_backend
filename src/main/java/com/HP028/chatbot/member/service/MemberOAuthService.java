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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberOAuthService {

    private final MemberRepository memberRepository;
    private final NaverOAuthClient naverOAuthClient;
    private final KakaoOAuthClient kakaoOAuthClient;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public MemberOAuthAccessResponse OAuthSignUp(MemberOAuthAccessRequest request, Provider provider) throws JsonProcessingException {

        OAuthUserInfo userInfo = getUserInfo(provider, request.getAccessToken());

        // 이미 가입된 회원인지 확인
        Optional<Member> existingMember = memberRepository.findByEmail(userInfo.getEmail());

        Member member;
        if (existingMember.isPresent()) {
            // 이미 가입된 회원이면 로그인 처리
            member = existingMember.get();
        } else {
            member = Member.createOAuthMember(userInfo, provider);
            member = memberRepository.save(member);
        }

        log.info("소셜 로그인 이메일={}",member.getEmail());
        // SecurityContext에 인증 정보 저장
        Authentication authentication = createAuthentication(member);
        log.info("authentication={}",authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new MemberOAuthAccessResponse(createAccessResponse(member));
    }

    private OAuthUserInfo getUserInfo(Provider provider, String accessToken) throws JsonProcessingException {

        return switch (provider) {
            case NAVER -> naverOAuthClient.getUserInfo(accessToken);
            case KAKAO -> kakaoOAuthClient.getUserInfo(accessToken);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private Authentication createAuthentication(Member member) {

        UserDetails userDetails = new User(member.getEmail(), passwordEncoder.encode(""),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + member.getRole().name())));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String createAccessResponse(Member member) {
        return jwtUtil.createJwt(member.getEmail(), member.getRole().toString());
    }

}