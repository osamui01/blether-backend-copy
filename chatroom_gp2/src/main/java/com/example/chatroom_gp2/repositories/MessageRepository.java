package com.example.chatroom_gp2.repositories;

import com.example.chatroom_gp2.components.DataLoader;
import com.example.chatroom_gp2.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatroomId(long id);

    List<Message> findByUserId(long id);

    List<Message> findByContentContains(String content);

}
