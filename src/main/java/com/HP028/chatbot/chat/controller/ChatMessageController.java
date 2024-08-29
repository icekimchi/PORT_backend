package com.HP028.chatbot.chat.controller;

import com.HP028.chatbot.chat.dto.ChatMessageDto;
import com.HP028.chatbot.chat.dto.SendChatMessageRequest;
import com.HP028.chatbot.chat.dto.SendChatMessageResponse;
import com.HP028.chatbot.chat.service.ChatMessageService;
import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.common.response.ApiSuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<ApiResponse<SendChatMessageResponse>> sendAndReceiveMessage(@RequestBody @Valid SendChatMessageRequest request) {
        return ApiResponse.success(ApiSuccessStatus.MESSAGE_SEND_AND_RECEIVE_SUCCESS,chatMessageService.sendMessage(request));
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<ApiResponse<List<ChatMessageDto>>> getChatMessages(@PathVariable Long chatRoomId){
        return ApiResponse.success(ApiSuccessStatus.RETRIEVE_SUCCESS, chatMessageService.getChatMessages(chatRoomId));
    }
}
