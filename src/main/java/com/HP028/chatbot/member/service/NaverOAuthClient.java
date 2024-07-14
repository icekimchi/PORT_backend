package com.HP028.chatbot.member.service;

import com.HP028.chatbot.member.domain.OAuthUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
@RequiredArgsConstructor
public class NaverOAuthClient implements OAuthClient {

    private final RestTemplate restTemplate;
    private final String USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";
    private final ObjectMapper objectMapper;


    @Override
    public OAuthUserInfo getUserInfo(String accessToken) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                USER_INFO_URL, HttpMethod.GET, new HttpEntity<>(null, headers),
                String.class);

        String responseBody = response.getBody();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String name = jsonNode.get("response")
                .get("name").asText();
        String email = jsonNode.get("response")
                .get("email").asText();
        return new OAuthUserInfo(email, name);
    }
}