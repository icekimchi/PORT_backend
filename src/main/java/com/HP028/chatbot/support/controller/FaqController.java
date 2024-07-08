package com.HP028.chatbot.support.controller;

import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.support.dto.CreateFaqRequest;
import com.HP028.chatbot.support.dto.CreateFaqResponse;
import com.HP028.chatbot.support.dto.FaqDto;
import com.HP028.chatbot.support.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.HP028.chatbot.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/support/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateFaqResponse>> createFaq(@RequestPart CreateFaqRequest request,
                                                                    @RequestPart List<MultipartFile> imageFiles,
                                                                    @RequestPart List<MultipartFile> attachFiles) throws IOException {
        return ApiResponse.success(CREATE_SUCCESS, faqService.save(request, imageFiles, attachFiles));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FaqDto>>> findAll() {
        return ApiResponse.success(RETRIEVE_SUCCESS, faqService.findAll());
    }
}
