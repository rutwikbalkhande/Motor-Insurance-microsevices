package com.example.User.kafka;

import com.example.User.entity.ClaimDTO;
import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import com.example.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/kafkauser")
@RequiredArgsConstructor
public class UserKafkaController {

    @Autowired
    private UserService userService;

    private final UserProducer userProducer;
    private final ClaimConsumer claimConsumer;

    //Kafka Producer
    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody User user) {
        userProducer.sendMessage(user);

        return ResponseEntity.ok("Message sent: " + user);
    }


    //check last user  message comes from Claim Serivice
    @GetMapping("/consumerClaim")
    public ClaimDTO getLastReceivedUser() {
         ClaimDTO claim = claimConsumer.getLastReceivedUser();
        if(claim == null){
            throw new RuntimeException("user claim not available" + claim);
        }
        return  claim;
    }

}
