package com.example.User.kafka;

import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import com.example.User.repository.UserRepository;
import com.example.User.service.UserService;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

//Producer
    public User sendMessage(User user) {


        User savedUser = userRepo.save(user);    // Save to database first

        kafkaTemplate.send("test-topic", savedUser); // Then send Kafka message (use saved entity so it has an ID)

        System.out.println("User saved & sent to Kafka notify Policy-Service: " + savedUser);
        return savedUser;
}
}