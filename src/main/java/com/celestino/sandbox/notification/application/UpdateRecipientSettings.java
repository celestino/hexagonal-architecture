package com.celestino.sandbox.notification.application;

import com.celestino.sandbox.notification.application.port.RecipientDTO;
import com.celestino.sandbox.notification.application.port.RecipientSettingsWriter;
import com.celestino.sandbox.notification.domain.service.RecipientSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateRecipientSettings implements RecipientSettingsWriter {
    public final RecipientSettingsService service;

    @Autowired
    public UpdateRecipientSettings(RecipientSettingsService service) {
        this.service = service;
    }

    @Override
    public void updateRecipientSettings(RecipientDTO recipient) {
        service.saveRecipientSettings(recipient.toModel());
    }
}
