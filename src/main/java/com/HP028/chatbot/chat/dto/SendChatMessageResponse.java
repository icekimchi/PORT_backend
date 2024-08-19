package com.HP028.chatbot.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendChatMessageResponse {

    private String userMessage;

    private String serverMessage;

    private LocalDateTime userMessageTime;

    private LocalDateTime serverMessageTime;
}
