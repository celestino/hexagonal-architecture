package com.celestino.sandbox.notification.infrastructure.config;

import com.celestino.sandbox.notification.infrastructure.config.KafkaConfiguration;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Configuration
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    private final KafkaConfiguration kafkaConfiguration;

    @Autowired
    public KafkaProducerConfig(KafkaConfiguration kafkaConfiguration, KafkaProperties kafkaProperties) {
        this.kafkaConfiguration = kafkaConfiguration;
        this.kafkaProperties = kafkaProperties;
    }

    private ProducerFactory<String, byte[]> producerFactory() {
        Map<String, Object> props =new HashMap<>(kafkaProperties.buildProducerProperties());
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic notificationTopic() {
        return new NewTopic(kafkaConfiguration.topicName(), 1, (short) 1);
    }
}
