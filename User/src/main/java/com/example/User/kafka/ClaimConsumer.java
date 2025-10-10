package com.example.User.kafka;

import com.example.User.entity.ClaimDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class ClaimConsumer {

    private ClaimDTO lastReceivedClaim; // store last message received

    @KafkaListener(topics= "claim-topic", groupId= "claim-user-consumer-group")
    public void consumerClaim(ClaimDTO claimDto)
    {
        System.out.println("Claim policy status updated: " + claimDto);
        this.lastReceivedClaim = claimDto;
    }

    public ClaimDTO getLastReceivedUser() {
        return lastReceivedClaim;
    }

}
