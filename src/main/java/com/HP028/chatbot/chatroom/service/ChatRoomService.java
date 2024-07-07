package com.HP028.chatbot.chatroom.service;

import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.chatroom.dto.ChatRoomRequest;
import com.HP028.chatbot.chatroom.dto.ChatRoomResponse;
import com.HP028.chatbot.chatroom.repository.ChatRoomRepository;
import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.config.jwt.JwtUtil;
import com.HP028.chatbot.exception.BadRequestException;
import com.HP028.chatbot.exception.NotFoundException;
import com.HP028.chatbot.member.domain.Member;
import com.HP028.chatbot.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public ChatRoomResponse createChatRoom(ChatRoomRequest request) {
        Member member = getLoginMember();
        if(chatRoomRepository.existsByRoomName(request.getRoomName())){
            throw new BadRequestException(ApiFailStatus.DUPLICATED_CHATROOM);
        }
        ChatRoom chatRoom = ChatRoom.createChatRoom(request.getRoomName(), member);
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName());
    }

    @Transactional(readOnly = true)
    public List<ChatRoomResponse> getChatRooms() {
        Member member = getLoginMember();
        return member.getChatRooms().stream()
                .filter(chatRoom -> !chatRoom.isDeleted())
                .map(chatRoom -> new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName()))
                .collect(Collectors.toList());
    }

    public ChatRoomResponse deleteChatRoom(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApiFailStatus.CHATROOM_NOT_FOUND));
        chatRoom.deleteChatRoom();
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName());
    }

    public ChatRoomResponse updateChatRoom(Long id, ChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ApiFailStatus.CHATROOM_NOT_FOUND));
        chatRoom.updateChatRoomName(request.getRoomName());
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName());
    }

    private Member getLoginMember() {
        return memberRepository.findByEmail(JwtUtil.getMemberEmailFromToken()).
                orElseThrow(() -> new NotFoundException(ApiFailStatus.MEMBER_NOT_FOUND));
    }
}
