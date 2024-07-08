package com.HP028.chatbot.support.domain;

import jakarta.persistence.*;

@Entity
public class Answer extends BasePost {

    @OneToOne
    @JoinColumn(name = "question_id")
    private Qna question;

}