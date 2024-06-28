package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.dto.SendChatMessageRequest;
import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import org.springframework.stereotype.Service;

@Service
public class LLMService {

    public LLMMessageResponse sendMessage(SendChatMessageRequest request) {
        //todo LLM 호출 및 응답 처리
        return new LLMMessageResponse(200, "success", "response message");
    }
}
