package com.HP028.chatbot.support.service;

import com.HP028.chatbot.common.S3Service;
import com.HP028.chatbot.common.response.ApiFailStatus;
import com.HP028.chatbot.exception.InternalServerException;
import com.HP028.chatbot.support.domain.MediaFile;
import com.HP028.chatbot.support.domain.Faq;
import com.HP028.chatbot.support.domain.FileType;
import com.HP028.chatbot.support.dto.*;
import com.HP028.chatbot.support.repository.FaqRepository;
import com.HP028.chatbot.support.repository.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FaqService {

    private final FaqRepository faqRepository;
    private final MediaFileRepository mediaFileRepository;
    private final S3Service s3Service;

    @Transactional
    public CreateFaqResponse save(CreateFaqRequest request, List<MultipartFile> imageFiles, List<MultipartFile> attachFiles){
        Faq faq = request.toEntity();
        processFiles(imageFiles, FileType.IMAGE, faq);
        processFiles(attachFiles, FileType.ATTACHMENT, faq);
        Faq savedFaq = faqRepository.save(faq);
        return CreateFaqResponse.from(savedFaq);
    }

    public List<FaqDto> findAll() {
        List<Faq> faqs = faqRepository.findAll();
        return faqs.stream().map(FaqDto::from).toList();
    }

    private void processFiles(List<MultipartFile> files, FileType fileType, Faq faq){
        if (files.isEmpty()) {
            return;
        }

        files.forEach(file -> {
            try {
                String fileUrl = s3Service.uploadFile(file);
                faq.addMediaFile(new MediaFile(file.getOriginalFilename(), fileUrl, fileType));
            } catch (IOException e) {
                throw new InternalServerException(ApiFailStatus.FILE_UPLOAD_ERROR, e);
            }
        });
    }


    @Transactional
    public DeleteFaqResponse delete(Long faqId) {
        Faq findFaq = faqRepository.findByIdOrThrow(faqId);
        findFaq.getMediaFiles().forEach(mediaFile -> s3Service.deleteFile(mediaFile.getFileUrl()));
        faqRepository.delete(findFaq);
        return new DeleteFaqResponse(findFaq.getId());
    }

    @Transactional
    public UpdateFaqResponse update(UpdateFaqRequest request, List<MultipartFile> imageFiles, List<MultipartFile> attachFiles, Long faqId) {
        Faq faq = faqRepository.findByIdOrThrow(faqId);
        faq.update(request.getTitle(), request.getContent());
        request.getDeleteMediaFileUrl().forEach(url->{
            mediaFileRepository.deleteByFileUrl(url);
            s3Service.deleteFile(url);
        });

        processFiles(imageFiles, FileType.IMAGE, faq);
        processFiles(attachFiles, FileType.ATTACHMENT, faq);

        return UpdateFaqResponse.from(faq);
    }
}