package com.example.chatroom_gp2.services;


import com.example.chatroom_gp2.models.Chatroom;
import com.example.chatroom_gp2.models.Message;
import com.example.chatroom_gp2.models.MessageDTO;
import com.example.chatroom_gp2.models.User;
import com.example.chatroom_gp2.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    UserService userService;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> findMessage(long id){
        return messageRepository.findById(id);
    }

   public Message saveMessage(MessageDTO messageDTO){
       Chatroom chatroom = chatroomService.getChatroomById(messageDTO.getChatroomId()).get();
       User user = userService.getUserById(messageDTO.getUserId()).get();
       int userAge = userService.calculateAge(user.getDateOfBirth());
       if(userAge >= chatroom.getAgeLimit()) {
           Message message = new Message(messageDTO.getContent(), chatroom, user);
           messageRepository.save(message);
           return message;
       } else {
           return null;
       }
   }

   public List<Message> findAllMessages(){
        return messageRepository.findAll();
   }

   public Message updateMessage(MessageDTO messageDTO, long id){
        Message messageUpdate = messageRepository.findById(id).get();
        messageUpdate.setContent(messageDTO.getContent());
        messageRepository.save(messageUpdate);
        return messageUpdate;
   }

   public void deleteMessage(long id){
      messageRepository.deleteById(id);
   }

   public List<Message> getMessagesByChatroomId(long id){
        return messageRepository.findByChatroomId(id);
   }

    public List<Message> getMessagesByUserId(long id){
        return messageRepository.findByUserId(id);
    }

    public List<Message> getMessagesByContent(String content){
        return messageRepository.findByContentContains(content);
    }

}
