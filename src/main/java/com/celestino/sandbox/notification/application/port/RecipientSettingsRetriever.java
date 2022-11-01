package com.celestino.sandbox.notification.application.port;

import java.util.Optional;

public interface RecipientSettingsRetriever {
    Optional<RecipientDTO> retrieveRecipientSettings(Long id);
}
