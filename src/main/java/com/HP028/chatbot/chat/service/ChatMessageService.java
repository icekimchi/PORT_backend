package com.HP028.chatbot.chat.service;

import com.HP028.chatbot.chat.domain.ChatMessage;
import com.HP028.chatbot.chat.dto.SendChatMessageRequest;
import com.HP028.chatbot.chat.dto.LLMMessageResponse;
import com.HP028.chatbot.chat.dto.SendChatMessageResponse;
import com.HP028.chatbot.chat.repository.ChatMessageRepository;
import com.HP028.chatbot.chatroom.domain.ChatRoom;
import com.HP028.chatbot.chatroom.repository.ChatRoomRepository;
import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final LLMService llmService;

    public SendChatMessageResponse sendMessage(SendChatMessageRequest request) {

        //회원 메시지 저장
        ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
                .orElseThrow(() -> new NotFoundException(ApiFailStatus.CHATROOM_NOT_FOUND));
        ChatMessage userMessage = ChatMessage.createUserMessage(request.getChatMessage(), chatRoom);
        chatMessageRepository.save(userMessage);

        //LLM 호출
        LLMMessageResponse LLMMessageResponse = llmService.sendMessage(request);

        //LLM 메시지 저장
        ChatMessage llmMessage = ChatMessage.createServerMessage(LLMMessageResponse.getChatMessage(), chatRoom);
        chatMessageRepository.save(llmMessage);

        return new SendChatMessageResponse(userMessage.getMessage(), llmMessage.getMessage());
    }
}
