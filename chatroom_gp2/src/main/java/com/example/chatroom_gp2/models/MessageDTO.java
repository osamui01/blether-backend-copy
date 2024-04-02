package com.example.chatroom_gp2.models;

import java.time.LocalDateTime;

public class MessageDTO {

    private long userId;
    private long chatroomId;
    private String content;
    private LocalDateTime messageDateTime;

    public MessageDTO(long userId, long chatroomId, String content) {
        this.userId = userId;
        this.chatroomId = chatroomId;
        this.content = content;
        this.messageDateTime = LocalDateTime.now();
    }

    public MessageDTO(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(long chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }
}
