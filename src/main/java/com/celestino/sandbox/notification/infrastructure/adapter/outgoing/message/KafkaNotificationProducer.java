package com.celestino.sandbox.notification.infrastructure.adapter.outgoing.message;

import com.celestino.sandbox.notification.application.port.NotificationEventProducer;
import com.celestino.sandbox.notification.domain.model.Event;
import com.celestino.sandbox.notification.infrastructure.config.KafkaConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationProducer implements NotificationEventProducer {

    private final KafkaConfiguration kafka;

    private final KafkaTemplate<String, byte[]> template;

    @Autowired
    public KafkaNotificationProducer(KafkaConfiguration kafka, KafkaTemplate<String, byte[]> template) {
        this.kafka = kafka;
        this.template = template;
    }

    @Override
    public void produce(Event.Message message) {
        template.send(kafka.topicName(), "key-1", message.toByteArray());
    }
}
