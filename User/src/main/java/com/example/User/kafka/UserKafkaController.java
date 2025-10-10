package com.example.User.kafka;

import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/kafkauser")
public class UserKafkaController {

    @Autowired
    private UserProducer userProducer;

    //Kafka Producer
    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody User user) {
        userProducer.sendMessage(user);
        return ResponseEntity.ok("Message sent: " + user);
    }

}
