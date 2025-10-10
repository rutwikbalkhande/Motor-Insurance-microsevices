package com.example.Policy.kafka;

import com.example.Policy.entity.UserDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class KafkaConsumer {

    private UserDTO lastReceivedUser; // store last message received

    @KafkaListener(topics= "test-topic", groupId="policy-consumer-group")
    public void consumeUser(UserDTO userDto){
        System.out.println("Receive message from kafka test-topic- "+ userDto);
        this.lastReceivedUser = userDto;

    }

    //check last user message
    public UserDTO getLastReceivedUser() {
        return lastReceivedUser;
    }
}


