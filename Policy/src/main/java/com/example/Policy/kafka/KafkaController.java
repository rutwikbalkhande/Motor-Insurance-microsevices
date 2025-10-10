package com.example.Policy.kafka;

import com.example.Policy.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policy")
public class KafkaController {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @GetMapping("/kafka/status")
    public String kafkaStatus() {

        return  "Kafka Consumer in Policy Service is running...";
    }


    @GetMapping("/kafka/last-message")
    public UserDTO getLastMessage() {
        UserDTO user = kafkaConsumer.getLastReceivedUser();
        if (user == null) {
            throw new RuntimeException("No message received yet!" );
        }
        return user;
    }
}