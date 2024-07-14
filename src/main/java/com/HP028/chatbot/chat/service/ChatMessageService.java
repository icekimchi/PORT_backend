package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.domain.ChatMessage;
import com.HP028.chatbot.chat.dto.ChatMessageDto;
import com.HP028.chatbot.chat.dto.SendChatMessageRequest;
import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import com.HP028.chatbot.chat.dto.SendChatMessageResponse;
import com.HP028.chatbot.chat.repository.ChatMessageRepository;
import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.chatroom.repository.ChatRoomRepository;
import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final LLMService llmService;

    @Transactional
    public SendChatMessageResponse sendMessage(SendChatMessageRequest request) {

        //회원 메시지 저장
        ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
                .orElseThrow(() -> new NotFoundException(ApiFailStatus.CHATROOM_NOT_FOUND));
        ChatMessage userMessage = ChatMessage.createUserMessage(request.getChatMessage(), chatRoom);
        chatMessageRepository.save(userMessage);

        //LLM 호출
        LLMMessageResponse LLMMessageResponse = llmService.sendMessage(request.getChatMessage());

        //LLM 메시지 저장
        ChatMessage llmMessage = ChatMessage.createServerMessage(LLMMessageResponse.getBody().getChatMessage(), chatRoom);
        chatMessageRepository.save(llmMessage);

        return new SendChatMessageResponse(userMessage.getMessage(), llmMessage.getMessage());
    }

    public List<ChatMessageDto> getChatMessages(Long chatRoomId){

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new NotFoundException(ApiFailStatus.CHATROOM_NOT_FOUND));

        List<ChatMessage> chatMessages = chatRoom.getChatMessages();

        return chatMessages.stream()
                .map(chatMessage -> new ChatMessageDto(chatMessage.getMessage(), chatMessage.getSenderType(), chatMessage.getTimestamp()))
                .collect(Collectors.toList());
    }
}
