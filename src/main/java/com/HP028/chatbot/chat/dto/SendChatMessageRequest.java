package com.HP028.chatbot.chat.dto;

import lombok.Getter;

@Getter
public class SendChatMessageRequest {

    private String chatMessage;

    private Long chatRoomId;

}
