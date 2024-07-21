package com.HP028.chatbot.support.dto;

import com.HP028.chatbot.support.domain.Faq;
import com.HP028.chatbot.support.domain.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FaqDto {

    private String title;

    private String content;

    private List<String> imageUrls = new ArrayList<>();

    private List<String> attachmentUrls = new ArrayList<>();

    public static FaqDto from(Faq faq) {
        FaqDto faqDto = new FaqDto();
        faqDto.title = faq.getTitle();
        faqDto.content = faq.getContent();
        if (faq.getMediaFiles() == null) {
            return faqDto;
        }
        faq.getMediaFiles().forEach(mediaFile -> {
            if (mediaFile.getFileType().equals(FileType.IMAGE)) {
                faqDto.imageUrls.add(mediaFile.getFileUrl());
            } else {
                faqDto.attachmentUrls.add(mediaFile.getFileUrl());
            }
        });
        return faqDto;
    }
}
