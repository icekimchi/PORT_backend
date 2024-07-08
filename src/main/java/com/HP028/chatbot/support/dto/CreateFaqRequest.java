package com.HP028.chatbot.support.dto;

import com.HP028.chatbot.support.domain.Faq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFaqRequest {

    private String title;

    private String content;


    public Faq toEntity() {
        return new Faq(title, content);
    }
}
