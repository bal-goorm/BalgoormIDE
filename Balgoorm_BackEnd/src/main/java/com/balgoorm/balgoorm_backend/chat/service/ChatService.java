package com.balgoorm.balgoorm_backend.chat.service;

import com.balgoorm.balgoorm_backend.chat.model.entity.Chat;
import com.balgoorm.balgoorm_backend.chat.model.request.ChatRequest;
import com.balgoorm.balgoorm_backend.chat.repository.ChatRepository;
import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetails;
import com.balgoorm.balgoorm_backend.user.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat enterChat(ChatRequest chatRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //security 에서 유저정보 가져오기
        Chat chat = new Chat();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User currentUser = customUserDetails.getUser();
            chat.setSenderName(currentUser.getNickname());
        }
        chat.setChatBody(chatRequest.getChatBody());
        chat.setChatTime(LocalDateTime.now());

        Chat saved = chatRepository.save(chat);
        log.info("saved: {}", saved.getChatBody());

        return saved;
    }

    public List<Chat> getHistory() {
        PageRequest pageRequest = PageRequest.of(0, 100);
        return chatRepository.findLatelyChat(pageRequest);
    }

}
