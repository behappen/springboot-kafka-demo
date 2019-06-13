package com.example.demo.task;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducer {
	private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 向topic中发送消息
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void send () {
        //kafkaTemplate.send("test", "13213213");
    }

    /**
     * 向topic中发送消息
     */
    public void send (String topic, List<String> msgs) {
        msgs.forEach(msg -> kafkaTemplate.send(topic, msg));
    }
}
