package com.celestino.sandbox.notification.infrastructure.adapter.incoming.web;

import com.celestino.sandbox.notification.application.port.NotificationEventProducer;
import com.celestino.sandbox.notification.application.port.RecipientDTO;
import com.celestino.sandbox.notification.application.port.RecipientSettingsRetriever;
import com.celestino.sandbox.notification.application.port.RecipientSettingsWriter;
import com.celestino.sandbox.notification.domain.model.Event;
import com.celestino.sandbox.notification.domain.model.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class RecipientSettingsController {

    private final RecipientSettingsRetriever settingsRetriever;
    private final RecipientSettingsWriter settingsWriter;
    private final NotificationEventProducer eventProducer;

    @Autowired
    public RecipientSettingsController(RecipientSettingsRetriever settingsRetriever, RecipientSettingsWriter settingsWriter, NotificationEventProducer eventProducer) {
        this.settingsRetriever = settingsRetriever;
        this.settingsWriter = settingsWriter;
        this.eventProducer = eventProducer;
    }

    @GetMapping("/settings/{id}")
    private Mono<RecipientDTO> readRecipientSettings(@PathVariable Long id) {
        Optional<RecipientDTO> recipient = settingsRetriever.retrieveRecipientSettings(id);
        return recipient.map(Mono::just).orElseGet(Mono::empty);

    }

    @PostMapping("/settings")
    private Mono<RecipientDTO> writeRecipientSettings(@RequestBody RecipientDTO recipient) {
        settingsWriter.updateRecipientSettings(recipient);
        return Mono.just(recipient);

    }

    @PostMapping("/send/{id}/{author}/{message}")
    private ResponseEntity<String> sendMessage(@PathVariable String id, @PathVariable String author, @PathVariable String message) {
        Event.Message eventMessage = Event.Message.newBuilder()
                .setId(id)
                .setAuthor(author)
                .setContent(message)
                .build();

        eventProducer.produce(eventMessage);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{Event message:" + eventMessage.toString() + "}");
    }
}
