package com.HP028.chatbot.chat.service;


import com.HP028.chatbot.chat.dto.LLMMessageResponse;

public interface LLMService {
    public LLMMessageResponse sendMessage(String chatMessage);
}
