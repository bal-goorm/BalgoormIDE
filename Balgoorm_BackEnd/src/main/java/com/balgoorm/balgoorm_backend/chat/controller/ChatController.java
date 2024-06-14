package com.balgoorm.balgoorm_backend.chat.controller;

import com.balgoorm.balgoorm_backend.chat.model.request.ChatRequest;
import com.balgoorm.balgoorm_backend.chat.service.ChatService;
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
    private final ChatService chatService;

    @MessageMapping("/join")
    @SendTo("/sub/chat")
    public String joinChatRoom(ChatRequest chatRequest) {
        log.info("Message: {}", chatRequest.getChatBody());
        return chatRequest.getSenderName() + "님이 입장하셨습니다.";
    }

    @MessageMapping("chat")
    @SendTo("/sub/chat")
    public String enterChat(ChatRequest chatRequest) {
        chatService.enterChat(chatRequest);
        log.info("Message: {}", chatRequest.getChatBody());
        return chatRequest.getSenderName() + ": " + chatRequest.getChatBody();
    }




/*        @MessageMapping("/join")
    public void joinChatRoom(ChatRequest chatRequest) {
        simpMessagingTemplate.convertAndSend("/sub/chat", chatRequest.getName() + "님이 입장하셨습니다.");
        log.info("Message {}", chatRequest.getMessage());
    }*/
}
