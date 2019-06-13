package com.example.demo.task;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 监听topic1主题,单条消费
     */
    @KafkaListener(topics = "test")
    public void listen0(ConsumerRecord<String, String> record) {
        consumer(record);
    }

    /**
     * 监听topic2主题,单条消费
     */
    @KafkaListener(topics = "${topicName.topic2}")
    public void listen1(ConsumerRecord<String, String> record) {
        consumer(record);
    }

    /**
     * 监听topic3和topic4,单条消费
     */
    @KafkaListener(topics = {"topic3", "topic4"})
    public void listen2(ConsumerRecord<String, String> record) {
        consumer(record);
    }

    /**
     * 监听topic5,批量消费
     */
    @KafkaListener(topics = "${topicName.topic2}", containerFactory = "batchFactory")
    public void listen2(List<ConsumerRecord<String, String>> records) {
        batchConsumer(records);
    }

    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, String> record) {
        logger.info("主题:{}, 内容: {}", record.topic(), record.value());
    }

    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, String>> records) {
        records.forEach(record -> consumer(record));
    }
}
