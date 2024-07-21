package com.HP028.chatbot.support.repository;

import com.HP028.chatbot.exception.NotFoundException;
import com.HP028.chatbot.support.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.HP028.chatbot.common.response.ApiFailStatus.FAQ_NOT_FOUND;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    default Faq findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(()->new NotFoundException(FAQ_NOT_FOUND));
    }
}
