package com.HP028.chatbot.support.dto;

import com.HP028.chatbot.support.domain.Faq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFaqResponse {

    private String title;

    private String content;

    public static UpdateFaqResponse from(Faq faq) {
        UpdateFaqResponse response = new UpdateFaqResponse();
        response.title = faq.getTitle();
        response.content = faq.getContent();
        return response;
    }
}
