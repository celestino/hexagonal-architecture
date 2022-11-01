package com.celestino.sandbox.notification.application;

import com.celestino.sandbox.notification.application.port.RecipientDTO;
import com.celestino.sandbox.notification.application.port.RecipientSettingsRetriever;
import com.celestino.sandbox.notification.domain.model.Recipient;
import com.celestino.sandbox.notification.domain.service.RecipientSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RetrieveRecipientSettings implements RecipientSettingsRetriever {
    public final RecipientSettingsService service;

    @Autowired
    public RetrieveRecipientSettings(RecipientSettingsService service) {
        this.service = service;
    }

    @Override
    public Optional<RecipientDTO> retrieveRecipientSettings(Long id) {
        Optional<Recipient> recipient = service.getRecipientByIdentifier(id);
        return recipient.map(value -> new RecipientDTO(value.getName()));
    }
}
