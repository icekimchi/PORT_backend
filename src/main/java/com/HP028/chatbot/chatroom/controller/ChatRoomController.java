package com.HP028.chatbot.chatroom.controller;

import com.HP028.chatbot.chatroom.dto.ChatRoomListResponse;
import com.HP028.chatbot.chatroom.dto.ChatRoomRequest;
import com.HP028.chatbot.chatroom.dto.ChatRoomResponse;
import com.HP028.chatbot.chatroom.service.ChatRoomService;
import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.common.response.ApiSuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatrooms")
public class ChatRoomController{

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ApiResponse<ChatRoomResponse>> createChatRoom(@RequestBody @Valid ChatRoomRequest request) {
        return ApiResponse.success(ApiSuccessStatus.CREATE_CHATROOM_SUCCESS,chatRoomService.createChatRoom(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ChatRoomListResponse>> getChatRooms() {
        return ApiResponse.success(ApiSuccessStatus.RETRIEVE_SUCCESS,chatRoomService.getChatRooms());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatRoomResponse>> deleteChatRoom(@PathVariable Long id) {
        return ApiResponse.success(ApiSuccessStatus.DELETE_SUCCESS,chatRoomService.deleteChatRoom(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatRoomResponse>> updateChatRoomName(@PathVariable Long id, @RequestBody @Valid ChatRoomRequest request) {
        return ApiResponse.success(ApiSuccessStatus.UPDATE_SUCCESS,chatRoomService.updateChatRoom(id, request));
    }
}
