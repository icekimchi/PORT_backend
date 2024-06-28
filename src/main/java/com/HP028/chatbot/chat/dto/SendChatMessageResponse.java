package com.HP028.chatbot.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendChatMessageResponse {

    private String userMessage;

    private String serverMessage;
}
