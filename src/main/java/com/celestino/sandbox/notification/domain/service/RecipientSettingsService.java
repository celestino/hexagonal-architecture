package com.celestino.sandbox.notification.domain.service;

import com.celestino.sandbox.notification.domain.exception.RecipientNotFoundException;
import com.celestino.sandbox.notification.domain.model.Recipient;
import com.celestino.sandbox.notification.domain.port.RecipientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RecipientSettingsService {

    Logger logger = LoggerFactory.getLogger(RecipientSettingsService.class);

    private final RecipientRepository repository;

    @Autowired
    public RecipientSettingsService(RecipientRepository repository) {
        this.repository = repository;
    }

    public Optional<Recipient> getRecipientByIdentifier(Long identifier) {
        try {
            return Optional.ofNullable(repository.getById(identifier));
        } catch (RecipientNotFoundException exception) {
            logger.warn(exception.getMessage());
            return Optional.empty();
        }
    }

    public Recipient saveRecipientSettings(Recipient recipient) {
        repository.save(recipient);
        return recipient;
    }
}
