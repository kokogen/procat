package org.koko.procat.config;

import org.koko.procat.dto.kafka.SimpleMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Value("${topic-name}")
    private String topic;
/*
    @Bean
    public RecordMessageConverter typeConverter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("org.koko.procat.dto.kafka");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("simple", SimpleMessageDTO.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

 */
    @Bean
    public ConsumerFactory<String, SimpleMessageDTO> batchConsumerFactory() {
        final JsonDeserializer<SimpleMessageDTO> jsonDeserializer = new JsonDeserializer<>(SimpleMessageDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.setUseTypeMapperForKey(true);
        jsonDeserializer.addTrustedPackages("org.koko.procat.dto.kafka");
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SimpleMessageDTO> simpleKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SimpleMessageDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(batchConsumerFactory());
        factory.setBatchListener(true);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        //factory.setMessageConverter(typeConverter());
        //factory.setConcurrency(2);
        //factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
/*
    @Bean
    public ConcurrentMessageListenerContainer<String, SimpleMessageDTO> simpleKafkaListenerContainer(
            ConcurrentKafkaListenerContainerFactory<String, SimpleMessageDTO> factory) {
        ConcurrentMessageListenerContainer<String, SimpleMessageDTO> container = factory.createContainer(topic);
        container.setupMessageListener(new BatchKafkaListener<>());
        return container;
    }
*/

}
