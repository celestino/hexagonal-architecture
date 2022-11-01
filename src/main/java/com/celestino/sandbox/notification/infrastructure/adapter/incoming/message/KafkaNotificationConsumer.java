package com.celestino.sandbox.notification.infrastructure.adapter.incoming.message;

import com.celestino.sandbox.notification.application.port.NotificationPrinter;
import com.celestino.sandbox.notification.application.port.NotificationPrinterDTO;
import com.celestino.sandbox.notification.domain.model.Event;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaNotificationConsumer {

    private final NotificationPrinter printer;

    @Autowired
    public KafkaNotificationConsumer(NotificationPrinter printer) {
        this.printer = printer;
    }

    @KafkaListener(topics = "notification-topic",
            groupId = "notification-center",
            containerFactory = "stringKafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, byte[]> consumerRecord) throws InvalidProtocolBufferException {
        Event.Message message = Event.Message.parseFrom(consumerRecord.value());
        NotificationPrinterDTO dataTransfer = new NotificationPrinterDTO(message.getId(), message.getAuthor(), message.getContent());
        printer.print(dataTransfer);
    }
}
