package com.HP028.chatbot.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LLMMessageResponse {

    private int status;

    private String message;

    private String chatMessage;
}
