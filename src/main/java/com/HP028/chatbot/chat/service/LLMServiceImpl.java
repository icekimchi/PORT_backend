package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.dto.LLMMessageRequest;
import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LLMServiceImpl implements LLMService{

    private final WebClient webClient;

    public LLMMessageResponse sendMessage(String chatMessage) {
        return webClient.post()
                .uri("/chat")
                .bodyValue(new LLMMessageRequest(chatMessage))
                .retrieve()
                .bodyToMono(LLMMessageResponse.class)
                .block();
    }
}