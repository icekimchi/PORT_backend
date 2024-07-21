package com.HP028.chatbot.support.controller;

import com.HP028.chatbot.common.response.ApiResponse;
import com.HP028.chatbot.support.dto.*;
import com.HP028.chatbot.support.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.HP028.chatbot.common.response.ApiSuccessStatus.*;

@RestController
@RequestMapping("/support/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateFaqResponse>> createFaq(@RequestPart CreateFaqRequest request,
                                                                    @RequestPart(required = false) List<MultipartFile> imageFiles,
                                                                    @RequestPart(required = false) List<MultipartFile> attachFiles){
        return ApiResponse.success(CREATE_SUCCESS, faqService.save(request, imageFiles, attachFiles));
    }

    @PutMapping("/{faqId}")
    public ResponseEntity<ApiResponse<UpdateFaqResponse>> updateFaq(@RequestPart UpdateFaqRequest request,
                                              @RequestPart(required = false) List<MultipartFile> imageFiles,
                                              @RequestPart(required = false) List<MultipartFile> attachFiles,
                                              @PathVariable Long faqId) {
        return ApiResponse.success(UPDATE_SUCCESS, faqService.update(request, imageFiles, attachFiles, faqId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FaqDto>>> findAll() {
        return ApiResponse.success(RETRIEVE_SUCCESS, faqService.findAll());
    }

    @DeleteMapping("/{faqId}")
    public ResponseEntity<ApiResponse<DeleteFaqResponse>> deleteFaq(@PathVariable Long faqId) {
        return ApiResponse.success(DELETE_SUCCESS,faqService.delete(faqId));
    }
}
