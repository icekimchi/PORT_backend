package com.HP028.chatbot.chatroom.domain;

import com.HP028.chatbot.chat.domain.ChatMessage;
import com.HP028.chatbot.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(unique = true)
    private String roomName;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member owner;

    private boolean isDeleted;

    public static ChatRoom createChatRoom(String roomName, Member owner) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomName = roomName;
        chatRoom.owner = owner;
        chatRoom.isDeleted = false;
        owner.getChatRooms().add(chatRoom);
        return chatRoom;
    }

    public void deleteChatRoom() {
        isDeleted = true;
    }

    public void updateChatRoomName(String roomName) {
        this.roomName = roomName;
    }
}
