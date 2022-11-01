package com.celestino.sandbox.notification.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="sandbox.kafka")
public record KafkaConfiguration(String topicName) {
}
