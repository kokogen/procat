package org.koko.procat.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.koko.procat.dto.kafka.SimpleMessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BatchKafkaListener extends AbstractConsumerSeekAware{

    @KafkaListener(
            id = "simpleListener",
            topics = "procat",
            containerFactory = "simpleKafkaListenerContainerFactory"
    )
    public void handle(List<SimpleMessageDTO> recs, Acknowledgment ack){
        recs.forEach(rec -> log.info(rec.toString()));
        ack.acknowledge();
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        super.onPartitionsAssigned(assignments, callback);
        assignments.forEach(
                    (tp, offset) -> {
                        log.info(
                                String.format(
                                    "assignment{topic: %s, partition: %s, offset: %d}, threadCallback: %s, callback: %s",
                                    tp.topic(), tp.partition(), offset, getSeekCallbackFor(tp), callback)
                        );
                        log.info(String.format("callbacks comparing: {$s}", getSeekCallbackFor(tp) == callback));
                    }
                );
    }

}
