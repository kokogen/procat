package org.koko.procat.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.koko.procat.dto.kafka.SimpleMessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

@Slf4j
public class BatchKafkaService {

    @KafkaListener(id = "manualAck", topics = "procat", containerFactory = "simpleKafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord<String, SimpleMessageDTO>> records, Acknowledgment ack){
        for(ConsumerRecord<String, SimpleMessageDTO> rec : records){
            log.info(String.format("key: %s, msg: %s, offset: %d", rec.key(), rec.value(), rec.offset()));

        }
    }
}
