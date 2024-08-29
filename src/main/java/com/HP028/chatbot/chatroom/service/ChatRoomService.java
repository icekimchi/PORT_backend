package com.HP028.chatbot.chatroom.service;

import com.HP028.chatbot.chat.domain.ChatMessage;
import com.HP028.chatbot.chat.dto.ChatMessageDto;
import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.chatroom.dto.ChatRoomListResponse;
import com.HP028.chatbot.chatroom.dto.ChatRoomRequest;
import com.HP028.chatbot.chatroom.dto.ChatRoomResponse;
import com.HP028.chatbot.chatroom.dto.ChatRoomWithLastMessageResponse;
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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public ChatRoomListResponse getChatRooms() {
        Member member = getLoginMember();

        List<ChatRoomWithLastMessageResponse> chatRoomsWithLastMessages = member.getChatRooms().stream()
                .filter(chatRoom -> !chatRoom.isDeleted())
                .map(chatRoom -> {
                    // ChatRoomResponse 생성
                    ChatRoomResponse chatRoomResponse = new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName());

                    // 마지막 메시지 가져오기
                    ChatMessage lastMessage = chatRoom.getChatMessages().stream()
                            .max(Comparator.comparing(ChatMessage::getTimestamp))
                            .orElse(null);

                    // 마지막 메시지가 없다면 null을 반환
                    ChatMessageDto lastMessageDto = (lastMessage != null)
                            ? new ChatMessageDto(lastMessage.getMessage(), lastMessage.getSenderType(), lastMessage.getTimestamp())
                            : null;

                    return new ChatRoomWithLastMessageResponse(chatRoomResponse, lastMessageDto);
                })
                .collect(Collectors.toList());

        return new ChatRoomListResponse(chatRoomsWithLastMessages);
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
        if(chatRoomRepository.existsByRoomName(request.getRoomName())){
            throw new BadRequestException(ApiFailStatus.DUPLICATED_CHATROOM);
        }
        chatRoom.updateChatRoomName(request.getRoomName());
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse(chatRoom.getId(), chatRoom.getRoomName());
    }

    private Member getLoginMember() {
        return memberRepository.findByEmail(JwtUtil.getMemberEmailFromToken()).
                orElseThrow(() -> new NotFoundException(ApiFailStatus.MEMBER_NOT_FOUND));
    }
}
