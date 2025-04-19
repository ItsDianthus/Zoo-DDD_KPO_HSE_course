package com.proj.KPO_DZ_2.domain.event;

import java.util.UUID;
import java.time.LocalDateTime;

public class AnimalMovedEvent implements DomainEvent {
    private final UUID animalId;
    private final UUID fromEnclosureId;
    private final UUID toEnclosureId;
    private final LocalDateTime occurredAt;

    public AnimalMovedEvent(UUID animalId, UUID fromEnclosureId, UUID toEnclosureId) {
        this.animalId = animalId;
        this.fromEnclosureId = fromEnclosureId;
        this.toEnclosureId = toEnclosureId;
        this.occurredAt = LocalDateTime.now();
    }

    public UUID getAnimalId() { return animalId; }
    public UUID getFromEnclosureId() { return fromEnclosureId; }
    public UUID getToEnclosureId() { return toEnclosureId; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
}