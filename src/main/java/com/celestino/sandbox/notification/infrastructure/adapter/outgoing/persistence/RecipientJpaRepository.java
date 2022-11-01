package com.celestino.sandbox.notification.infrastructure.adapter.outgoing.persistence;

import com.celestino.sandbox.notification.infrastructure.adapter.outgoing.persistence.entity.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface RecipientJpaRepository extends JpaRepository<RecipientEntity, Long> {
}
