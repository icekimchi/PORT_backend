package com.HP028.chatbot.chatroom.dto;

import com.HP028.chatbot.chat.dto.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ChatRoomListResponse {

    private List<ChatRoomWithLastMessageResponse> chatRooms;
}