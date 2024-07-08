package com.HP028.chatbot.support.service;

import com.HP028.chatbot.common.S3Service;
import com.HP028.chatbot.support.domain.Attachment;
import com.HP028.chatbot.support.domain.Faq;
import com.HP028.chatbot.support.domain.FileType;
import com.HP028.chatbot.support.dto.CreateFaqRequest;
import com.HP028.chatbot.support.dto.CreateFaqResponse;
import com.HP028.chatbot.support.dto.FaqDto;
import com.HP028.chatbot.support.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqService {

    private final FaqRepository faqRepository;
    private final S3Service s3Service;

    @Transactional
    public CreateFaqResponse save(CreateFaqRequest request, List<MultipartFile> imageFiles, List<MultipartFile> attachFiles) throws IOException {
        Faq faq = request.toEntity();
        for (MultipartFile file : imageFiles) {
            String fileUrl = s3Service.uploadFile(file);
            faq.addAttachment(new Attachment(file.getOriginalFilename(), fileUrl, FileType.IMAGE));
        }
        for (MultipartFile file : attachFiles) {
            String fileUrl = s3Service.uploadFile(file);
            faq.addAttachment(new Attachment(file.getOriginalFilename(), fileUrl, FileType.ATTACHMENT));
        }
        faqRepository.save(faq);
        return CreateFaqResponse.from(faq);
    }

    public List<FaqDto> findAll() {
        List<Faq> faqs = faqRepository.findAll();
        return faqs.stream().map(FaqDto::from).toList();
    }
}