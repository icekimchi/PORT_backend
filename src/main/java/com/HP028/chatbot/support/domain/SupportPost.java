package com.HP028.chatbot.support.domain;

import com.HP028.chatbot.common.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public class SupportPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String image;
}
