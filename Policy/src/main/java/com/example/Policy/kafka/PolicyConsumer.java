package com.example.Policy.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PolicyConsumer {

    @KafkaListener(topics= "user-topic", groupId="policy-group")
    public void consume(String messgae){
        System.out.println("receive message from kafka topic- "+ messgae);
    }
}
