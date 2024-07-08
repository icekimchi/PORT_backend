package com.HP028.chatbot.support.repository;

import com.HP028.chatbot.support.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq,Long> {
}
