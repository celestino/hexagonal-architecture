package com.celestino.sandbox.notification.infrastructure.adapter.outgoing.persistence;

import com.celestino.sandbox.notification.domain.model.Recipient;
import com.celestino.sandbox.notification.domain.port.RecipientRepository;
import com.celestino.sandbox.notification.infrastructure.adapter.outgoing.persistence.entity.RecipientEntity;
import com.celestino.sandbox.notification.domain.exception.RecipientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MySQLRecipientRepository implements RecipientRepository {
    private final RecipientJpaRepository entityRepository;

    @Autowired
    public MySQLRecipientRepository(RecipientJpaRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Recipient getById(Long id) throws RecipientNotFoundException {
        Optional<RecipientEntity> entity = entityRepository.findById(id);
        RecipientEntity recipient = entity.orElseThrow(() -> new RecipientNotFoundException(id));
        return recipient.toModel();
    }

    @Override
    public Recipient save(Recipient recipient) {
        RecipientEntity entity = RecipientEntity.from(recipient);
        entityRepository.save(entity);
        recipient.setId(entity.getId());
        return recipient;
    }
}
