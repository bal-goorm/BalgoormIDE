package com.balgoorm.balgoorm_backend.chat.repository;

import com.balgoorm.balgoorm_backend.chat.model.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
