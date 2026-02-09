package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listen(List<String> messages) {
        System.out.println("Получено из Kafka батч из " + messages.size() + " сообщений. Сообщения: " + messages);
    }
}
