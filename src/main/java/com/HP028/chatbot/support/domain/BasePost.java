package com.HP028.chatbot.support.domain;

import com.HP028.chatbot.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorColumn(name = "DTYPE")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
public abstract class BasePost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String title;

    protected String content;

    protected Boolean isDeleted;

    public BasePost(String title, String content) {
        this.title = title;
        this.content = content;
        this.isDeleted = false;
    }

}
