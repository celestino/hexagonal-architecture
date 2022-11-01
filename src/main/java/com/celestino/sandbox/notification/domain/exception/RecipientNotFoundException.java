package com.celestino.sandbox.notification.domain.exception;

public class RecipientNotFoundException extends Exception {

    public RecipientNotFoundException(Long recipientId) {
        this(recipientId, null);
    }

    public RecipientNotFoundException(Long recipientId, Throwable error) {
        super(String.format("Recipient with ID %s could not be found.", recipientId), error);
    }
}
