package com.celestino.sandbox.notification.domain.model;

import lombok.Data;

@Data
public class Recipient {
    private Long id;
    private String name;

    public Recipient (String name) {
        this(null, name);
    }

    public Recipient (Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
