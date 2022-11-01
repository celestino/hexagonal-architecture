package com.celestino.sandbox.notification.application.port;

import com.celestino.sandbox.notification.domain.model.Recipient;

public record RecipientDTO(String name) {
    public Recipient toModel() {
        return new Recipient(name());
    }
}
