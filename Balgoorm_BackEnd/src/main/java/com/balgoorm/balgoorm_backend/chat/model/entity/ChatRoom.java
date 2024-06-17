package com.balgoorm.balgoorm_backend.chat.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

//    @OneToMany(mappedBy = "chatroom")
//    private List<Chat> chats;

}
