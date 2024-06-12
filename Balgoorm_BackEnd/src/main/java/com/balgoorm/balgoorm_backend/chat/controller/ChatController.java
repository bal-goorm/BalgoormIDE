package com.balgoorm.balgoorm_backend.chat.controller;

import com.balgoorm.balgoorm_backend.chat.model.request.ChatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/join")
    @SendTo("/sub/chat")
    public String joinChatRoom(ChatRequest chatRequest) {
        log.info("Message: {}", chatRequest.getMessage());
        return chatRequest.getName() + "님이 입장하셨습니다.";
    }

    @MessageMapping("chat")
    @SendTo("/sub/chat")
    public String enterChat(ChatRequest chatRequest) {
        log.info("Message: {}", chatRequest.getMessage());
        return chatRequest.getName() + ": " + chatRequest.getMessage();
    }




/*        @MessageMapping("/join")
    public void joinChatRoom(ChatRequest chatRequest) {
        simpMessagingTemplate.convertAndSend("/sub/chat", chatRequest.getName() + "님이 입장하셨습니다.");
        log.info("Message {}", chatRequest.getMessage());
    }*/
}
