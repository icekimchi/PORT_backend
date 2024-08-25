package com.HP028.chatbot.chatroom.controller;

import com.HP028.chatbot.chatroom.dto.ChatRoomListResponse;
import com.HP028.chatbot.chatroom.dto.ChatRoomRequest;
import com.HP028.chatbot.chatroom.dto.ChatRoomResponse;
import com.HP028.chatbot.common.response.ApiFailStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "채팅방 관리 API", description = "채팅방 CRUD를 관리하는 API")
public interface ChatRoomApiSpecification {
    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 - 중복된 채팅방 이름",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"중복된 채팅방 이름입니다\"}")))
    })
    @PostMapping
    ResponseEntity<com.HP028.chatbot.common.response.ApiResponse<ChatRoomResponse>> createChatRoom(@RequestBody @Valid ChatRoomRequest request);


    @Operation(summary = "채팅방 목록 조회", description = "로그인한 사용자의 모든 채팅방 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 조회 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패 - 로그인 필요",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 401, \"message\": \"로그인이 필요합니다\"}")))
    })
    @GetMapping
    public ResponseEntity<com.HP028.chatbot.common.response.ApiResponse<ChatRoomListResponse>> getChatRooms();


    @Operation(summary = "채팅방 삭제", description = "특정 채팅방을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"존재하지 않는 채팅방입니다\"}"))),
            @ApiResponse(responseCode = "401", description = "인증 실패 - 로그인 필요",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 401, \"message\": \"로그인이 필요합니다\"}"))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 500, \"message\": \"서버 오류가 발생했습니다.\"}")))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<com.HP028.chatbot.common.response.ApiResponse<ChatRoomResponse>> deleteChatRoom(@PathVariable Long id);

    @Operation(summary = "채팅방 이름 수정", description = "특정 채팅방의 이름을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 이름 수정 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"존재하지 않는 채팅방입니다\"}"))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 - 중복된 채팅방 이름",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"중복된 채팅방 이름입니다\"}"))),
            @ApiResponse(responseCode = "401", description = "인증 실패 - 로그인 필요",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 401, \"message\": \"로그인이 필요합니다\"}"))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(examples = @ExampleObject(value = "{\"status\": 500, \"message\": \"서버 오류가 발생했습니다.\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<com.HP028.chatbot.common.response.ApiResponse<ChatRoomResponse>> updateChatRoomName(@PathVariable Long id, @RequestBody @Valid ChatRoomRequest request);
}
