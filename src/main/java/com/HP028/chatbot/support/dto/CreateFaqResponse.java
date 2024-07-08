package com.HP028.chatbot.support.dto;

import com.HP028.chatbot.support.domain.Faq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFaqResponse {

    private String title;

    private String content;

    public static CreateFaqResponse from(Faq faq) {
        CreateFaqResponse response = new CreateFaqResponse();
        response.title = faq.getTitle();
        response.content = faq.getContent();
        return response;
    }
}
