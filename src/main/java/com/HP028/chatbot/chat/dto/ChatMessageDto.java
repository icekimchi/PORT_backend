package com.HP028.chatbot.chat.dto;

import com.HP028.chatbot.chat.domain.SenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private String chatMessage;

    private SenderType senderType;

    private LocalDateTime timestamp;
}
