package com.example.chatroom_gp2.services;


import com.example.chatroom_gp2.models.Chatroom;
import com.example.chatroom_gp2.models.ChatroomDTO;
import com.example.chatroom_gp2.models.Message;
import com.example.chatroom_gp2.repositories.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatroomService {

    @Autowired
    ChatroomRepository chatroomRepository;

    public Chatroom saveChatroom(Chatroom chatroom){
        return chatroomRepository.save(chatroom);
    }

    public Optional<Chatroom> getChatroomById(long id){
        return chatroomRepository.findById(id);
    }

    public List<Chatroom> getAllChatrooms(){
        return chatroomRepository.findAll();
    }

    public Chatroom updateChatroom(Long id, ChatroomDTO chatroomDTO){
        Chatroom chatroomToUpdate = chatroomRepository.findById(id).get();
        chatroomToUpdate.setName(chatroomDTO.getName());
        chatroomToUpdate.setCapacity(chatroomDTO.getCapacity());
        chatroomToUpdate.setAgeLimit(chatroomDTO.getAgeLimit());
        chatroomRepository.save(chatroomToUpdate);
        return chatroomToUpdate;
    }

//    public void deleteChatroom(long id){
//        Chatroom chatroom = chatroomRepository.findById(id).get();
//        List<Message> messages = chatroom.getMessages();
//        for (Message message : messages){
//            messages.remove(message);
//        }
//        chatroomRepository.deleteById(id);
//    }

    public void deleteChatroom(long id){
        Chatroom chatroom = chatroomRepository.findById(id).get();
//        List<Message> messages = chatroom.getMessages();
//        var iterator = messages.iterator();
//        while(iterator.hasNext()){
//            Message message = iterator.next();
//            iterator.remove();
//        }
        chatroomRepository.deleteById(id);
    }

    public List<Chatroom> findChatroomByMostRecentMessage(){
       return chatroomRepository.findChatroomByMostRecentMessage();
    }

    public Chatroom findMostActiveChatroom(){
        return chatroomRepository.findMostActiveChatroom();
    }

}
