package com.HP028.chatbot.chat.domain;

import com.HP028.chatbot.chatroom.domain.ChatRoom;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.function.Consumer;

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

    public static ChatMessage createMessage(String message, ChatRoom chatRoom, Consumer<ChatMessage> typeSetter) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.chatRoom = chatRoom;
        chatMessage.timestamp = LocalDateTime.now();
        chatRoom.getChatMessages().add(chatMessage);
        typeSetter.accept(chatMessage);
        return chatMessage;
    }

    public static ChatMessage createUserMessage(String message, ChatRoom chatRoom) {
        return createMessage(message, chatRoom, chatMessage -> chatMessage.senderType = SenderType.USER);
    }

    public static ChatMessage createServerMessage(String message, ChatRoom chatRoom) {
        return createMessage(message, chatRoom, chatMessage -> chatMessage.senderType = SenderType.SERVER);
    }


}
