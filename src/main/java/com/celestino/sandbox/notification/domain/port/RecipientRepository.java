package com.celestino.sandbox.notification.domain.port;

import com.celestino.sandbox.notification.domain.model.Recipient;
import com.celestino.sandbox.notification.domain.exception.RecipientNotFoundException;

public interface RecipientRepository {

    public Recipient getById(Long id) throws RecipientNotFoundException;

    public Recipient save(Recipient recipient);
}
