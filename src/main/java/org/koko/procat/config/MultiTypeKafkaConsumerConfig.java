package org.koko.procat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class MultiTypeKafkaConsumerConfig {
/*
    @Value("${kafka.bootstrap-servers}") private String bootstrapServers;
    @Value("${kafka.group-id}") private String groupId;

    @Bean
    public RecordMessageConverter multiTypeConverter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("org.koko.procat.dto.kafka");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("catalogue", CatalogueDTO.class);
        mappings.put("manufacturer", ManufacturerDTO.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> multiTypeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setMessageConverter(multiTypeConverter());
        //factory.setConcurrency(2);
        return factory;
    }

 */
}
