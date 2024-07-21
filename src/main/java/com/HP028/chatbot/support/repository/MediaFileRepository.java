package com.HP028.chatbot.support.repository;

import com.HP028.chatbot.support.domain.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
    void deleteByFileUrl(String fileUrl);
}
