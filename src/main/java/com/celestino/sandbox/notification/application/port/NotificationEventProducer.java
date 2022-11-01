package com.celestino.sandbox.notification.application.port;

import com.celestino.sandbox.notification.domain.model.Event;

public interface NotificationEventProducer {

    public void produce(Event.Message message);
}
