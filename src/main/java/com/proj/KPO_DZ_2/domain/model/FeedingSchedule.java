package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.FoodType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FeedingSchedule {
    private final UUID id;
    private UUID animalId;
    private LocalDateTime feedingTime;
    private FoodType foodType;
    private boolean completed;

    public FeedingSchedule(UUID id,
                           UUID animalId,
                           LocalDateTime feedingTime,
                           FoodType foodType) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.animalId = Objects.requireNonNull(animalId, "animalId must not be null");
        this.feedingTime = Objects.requireNonNull(feedingTime, "feedingTime must not be null");
        this.foodType = Objects.requireNonNull(foodType, "foodType must not be null");
        this.completed = false;
    }

    public UUID getId() { return id; }
    public UUID getAnimalId() { return animalId; }
    public LocalDateTime getFeedingTime() { return feedingTime; }
    public FoodType getFoodType() { return foodType; }
    public boolean isCompleted() { return completed; }

    public void reschedule(LocalDateTime newTime) {
        this.feedingTime = Objects.requireNonNull(newTime, "newTime must not be null");
    }

    public void markCompleted() {
        this.completed = true;
    }
}