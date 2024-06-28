package com.HP028.chatbot.chat.controller;

import com.HP028.chatbot.chat.dto.SendChatMessageRequest;
import com.HP028.chatbot.chat.dto.SendChatMessageResponse;
import com.HP028.chatbot.chat.service.ChatMessageService;
import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.common.response.ApiSuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<ApiResponse<SendChatMessageResponse>> sendAndReceiveMessage(@RequestBody SendChatMessageRequest request) {
        return ApiResponse.success(ApiSuccessStatus.MESSAGE_SEND_AND_RECEIVE_SUCCESS,chatMessageService.sendMessage(request));
    }
}
