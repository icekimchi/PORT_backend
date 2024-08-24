package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import com.HP028.chatbot.chat.dto.LLMServerResponseBody;
import org.springframework.stereotype.Service;

@Service
public class LLMServiceDummy implements LLMService{
    @Override
    public LLMMessageResponse sendMessage(String chatMessage) {
        return new LLMMessageResponse("채팅 메시지");
    }
}
