package com.HP028.chatbot.support.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Faq extends BasePost {

    @OneToMany(mappedBy = "faq", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaFile> mediaFiles = new ArrayList<>();

    public Faq(String title, String content) {
        super(title, content);
    }

    public void addMediaFile(MediaFile mediaFile) {
        this.mediaFiles.add(mediaFile);
        mediaFile.setFaq(this);
    }

    public void update(String title, String content) {
        super.title = title;
        super.content = content;
    }

    public void delete() {
        super.isDeleted = true;
    }
}