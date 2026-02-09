package com.example.kafka;

import com.example.kafka.service.MessageStorageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {

    private final MessageStorageService messageStorageService;

    public KafkaConsumer(MessageStorageService messageStorageService) {
        this.messageStorageService = messageStorageService;
    }

    @KafkaListener(topics = "test", groupId = "my-group")
    public void listen(List<String> messages) {
        System.out.println("Получено из Kafka батч из " + messages.size() + " сообщений. Сообщения: " + messages);
        messageStorageService.saveAll(messages);
    }
}
