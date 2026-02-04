package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Получено из Kafka: " + message);
    }
}
