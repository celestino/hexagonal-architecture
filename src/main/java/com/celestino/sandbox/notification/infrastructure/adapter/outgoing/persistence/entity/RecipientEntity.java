package com.celestino.sandbox.notification.infrastructure.adapter.outgoing.persistence.entity;

import com.celestino.sandbox.notification.domain.model.Recipient;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="recipient")
@Data
public class RecipientEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public RecipientEntity() {
    }

    public RecipientEntity(String name) {
        this.name = name;
    }

    public static RecipientEntity from(Recipient recipient) {
        RecipientEntity entity = new RecipientEntity(recipient.getName());
        if (recipient.getId() != null) {
            entity.setId(recipient.getId());
        }
        return entity;
    }

    public Recipient toModel() {
        return new Recipient(getId(), getName());
    }
}
