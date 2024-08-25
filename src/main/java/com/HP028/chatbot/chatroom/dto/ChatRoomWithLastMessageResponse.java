package com.HP028.chatbot.chatroom.dto;

import com.HP028.chatbot.chat.dto.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomWithLastMessageResponse {

    private ChatRoomResponse chatRoom;
    private ChatMessageDto lastMessage;
}