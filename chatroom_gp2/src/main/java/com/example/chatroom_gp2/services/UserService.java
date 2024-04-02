package com.example.chatroom_gp2.services;


import com.example.chatroom_gp2.models.Message;
import com.example.chatroom_gp2.models.User;
import com.example.chatroom_gp2.models.UserDTO;
import com.example.chatroom_gp2.repositories.ChatroomRepository;
import com.example.chatroom_gp2.repositories.MessageRepository;
import com.example.chatroom_gp2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatroomRepository chatroomRepository;

    public User updateUser(UserDTO userDTO, long id){
        User userUpdate = userRepository.findById(id).get();
        userUpdate.setName(userDTO.getName());
        userUpdate.setEmail(userDTO.getEmail());
        userUpdate.setDateOfBirth(userDTO.getDateOfBirth());
        userRepository.save(userUpdate);
        return userUpdate;
    }

    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteUserById(long id){
        User user = userRepository.findById(id).get();
        for (Message message : user.getMessages()){
            messageRepository.deleteById(message.getId());
        }
        userRepository.deleteById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        if (dateOfBirth != null)
        {
            return Period.between(dateOfBirth, currentDate).getYears();
        }
        else {
            return 0;
        }
    }
}
