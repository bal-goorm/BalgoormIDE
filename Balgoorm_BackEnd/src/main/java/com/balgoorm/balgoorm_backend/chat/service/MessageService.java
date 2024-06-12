package com.balgoorm.balgoorm_backend.chat.service;

import com.balgoorm.balgoorm_backend.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

}
