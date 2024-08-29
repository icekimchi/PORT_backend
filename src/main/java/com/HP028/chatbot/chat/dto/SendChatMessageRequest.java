package com.HP028.chatbot.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SendChatMessageRequest {

    @NotBlank
    @Size(min = 1, max = 1000)
    private String chatMessage;

    @NotBlank
    private Long chatRoomId;

}
