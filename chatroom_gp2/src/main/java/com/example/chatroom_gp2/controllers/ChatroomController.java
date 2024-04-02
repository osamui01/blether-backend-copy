package com.example.chatroom_gp2.controllers;

import com.example.chatroom_gp2.models.Chatroom;
import com.example.chatroom_gp2.models.ChatroomDTO;
import com.example.chatroom_gp2.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chatrooms")
public class ChatroomController {

    @Autowired
    ChatroomService chatroomService;

    @PostMapping
    public ResponseEntity<Chatroom> saveChatroom(@RequestBody Chatroom chatroom){
        Chatroom newChatroom = chatroomService.saveChatroom(chatroom);
        return new ResponseEntity<>(newChatroom, HttpStatus.CREATED);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Chatroom> getChatroomById(@PathVariable long id) {
        Optional<Chatroom> chatroom = chatroomService.getChatroomById(id);
        if (chatroom.isPresent()) {
            return new ResponseEntity<>(chatroom.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Chatroom>> getAllChatRooms(){
        List<Chatroom> allChatrooms = chatroomService.getAllChatrooms();
        return new ResponseEntity<>(allChatrooms, HttpStatus.OK);
    }

    @PatchMapping (value = "/{id}")
    public ResponseEntity<Chatroom> updateChatroom(@PathVariable Long id, @RequestBody ChatroomDTO chatroomDTO){
       Optional<Chatroom> chatroom = chatroomService.getChatroomById(id);
        if (chatroom.isPresent()) {
            Chatroom updatedChatroom = chatroomService.updateChatroom(id, chatroomDTO);
            return new ResponseEntity<>(updatedChatroom, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteChatroom(@PathVariable Long id) {
        Optional<Chatroom> chatroom = chatroomService.getChatroomById(id);
        if (chatroom.isPresent()) {
            chatroomService.deleteChatroom(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/recent")
    public ResponseEntity<List<Chatroom>> getMostRecentChatroomsUsed(){
        List<Chatroom> mostRecentChatroomsUsed = chatroomService.findChatroomByMostRecentMessage();
        return new ResponseEntity<>(mostRecentChatroomsUsed, HttpStatus.OK);
    }

    @GetMapping(path = "/mostActive")
    public ResponseEntity<Chatroom> getMostActiveChatroom(){
        Chatroom mostActiveChatroom = chatroomService.findMostActiveChatroom();
        return new ResponseEntity<>(mostActiveChatroom, HttpStatus.OK);
    }

}
