package com.example.Claim_Module.kafka;

import com.example.Claim_Module.entity.Claim;
import com.example.Claim_Module.entity.ClaimDTO;
import com.example.Claim_Module.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claim/kafka")
public class KafkaClaimProducer {

    @Autowired
    private KafkaTemplate<String , Claim> kafkaTemplate;
    @Autowired
    private  ClaimService claimService;

    @PostMapping("/publish")
    public Claim sendMessage(@RequestBody Claim claimDto) {

        kafkaTemplate.send("claim-topic", claimDto);
        System.out.println(" Sent Claim message to Kafka User-Service: " + claimDto);

       // return " Sent Claim message to Kafka: " + claimDto ;

        Claim updateClaim = claimService.updateClaim(
                claimDto.getClaimId(),
                claimDto.getStatus(),
                claimDto.getClaimAmount()
        );
        System.out.println("Claim Updated Succesfully: "+ updateClaim);

       return updateClaim ;

    }

}
