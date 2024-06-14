package com.balgoorm.balgoorm_backend.chat.service;

import com.balgoorm.balgoorm_backend.chat.model.entity.Chat;
import com.balgoorm.balgoorm_backend.chat.model.request.ChatRequest;
import com.balgoorm.balgoorm_backend.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;

    public void enterChat(ChatRequest chatRequest) {
        Chat chat = new Chat();
        chat.setChatBody(chatRequest.getChatBody());
        chat.setSenderName(chatRequest.getSenderName());
        chat.setChatTime(LocalDateTime.now());

        Chat saved = chatRepository.save(chat);
        log.info("saved: {}", saved.getChatBody());
    }

}
