package com.HP028.chatbot.member.service;

import com.HP028.chatbot.member.domain.OAuthUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OAuthClient {
    OAuthUserInfo getUserInfo(String AccessToken) throws JsonProcessingException;
}
