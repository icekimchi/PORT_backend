package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import com.HP028.chatbot.chat.dto.LLMServerResponseBody;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class LLMServiceDummy implements LLMService{
    @Override
    public LLMMessageResponse sendMessage(String chatMessage) {
        return new LLMMessageResponse(200, "채팅 응답 성공", new LLMServerResponseBody("채팅 메시지"));
    }
}
