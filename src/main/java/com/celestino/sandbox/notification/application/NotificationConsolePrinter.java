package com.celestino.sandbox.notification.application;

import com.celestino.sandbox.notification.application.port.NotificationPrinter;
import com.celestino.sandbox.notification.application.port.NotificationPrinterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsolePrinter implements NotificationPrinter {

    Logger logger = LoggerFactory.getLogger(NotificationConsolePrinter.class);

    @Override
    public void print(NotificationPrinterDTO dto) {
        logger.info("Consumed message: " + dto.toString());
    }
}
