package com.HP028.chatbot.chat.domain;

import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private SenderType senderType;

    @JoinColumn(name = "chat_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    private LocalDateTime timestamp;

    public static ChatMessage createUserMessage(String message, ChatRoom chatRoom) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.senderType = SenderType.USER;
        chatMessage.chatRoom = chatRoom;
        chatMessage.timestamp = LocalDateTime.now();
        chatRoom.getChatMessages().add(chatMessage);
        return chatMessage;
    }

    public static ChatMessage createServerMessage(String message, ChatRoom chatRoom) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.senderType = SenderType.SERVER;
        chatMessage.chatRoom = chatRoom;
        chatMessage.timestamp = LocalDateTime.now();
        chatRoom.getChatMessages().add(chatMessage);
        return chatMessage;
    }

}
