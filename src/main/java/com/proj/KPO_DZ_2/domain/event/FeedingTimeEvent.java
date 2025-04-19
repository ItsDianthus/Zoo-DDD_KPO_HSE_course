package com.proj.KPO_DZ_2.domain.event;

import java.util.UUID;
import java.time.LocalDateTime;

public class FeedingTimeEvent implements DomainEvent {
    private final UUID scheduleId;
    private final LocalDateTime occurredAt;

    public FeedingTimeEvent(UUID scheduleId, LocalDateTime occurredAt) {
        this.scheduleId = scheduleId;
        this.occurredAt = occurredAt;
    }

    public UUID getScheduleId() { return scheduleId; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
}