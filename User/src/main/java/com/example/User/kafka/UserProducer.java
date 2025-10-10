package com.example.User.kafka;

import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

//Producer
    public void sendMessage(User user) {
        kafkaTemplate.send("test-topic", user);
        System.out.println(" Sent message to Kafka: " + user);
    }
}