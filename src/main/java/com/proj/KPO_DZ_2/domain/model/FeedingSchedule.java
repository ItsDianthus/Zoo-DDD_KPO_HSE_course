package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.FoodType;
import com.proj.KPO_DZ_2.domain.valueobjects.FeedingTime;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class FeedingSchedule {

    private final UUID id;
    private UUID animalId;
    private FeedingTime feedingTime;
    private FoodType foodType;
    private boolean completed;

    public FeedingSchedule(UUID id,
                           UUID animalId,
                           FeedingTime feedingTime,
                           FoodType foodType) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.animalId = Objects.requireNonNull(animalId, "animalId must not be null");
        this.feedingTime = Objects.requireNonNull(feedingTime, "feedingTime must not be null");
        this.foodType = Objects.requireNonNull(foodType, "foodType must not be null");
        this.completed = false;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAnimalId() {
        return animalId;
    }

    public FeedingTime getFeedingTime() {
        return feedingTime;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isCompleted() {
        return completed;
    }

    /** Перенести кормление на другое время */
    public void reschedule(FeedingTime newTime) {
        this.feedingTime = Objects.requireNonNull(newTime, "newTime must not be null");
    }

    /** Отметить как выполненное */
    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedingSchedule that = (FeedingSchedule) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FeedingSchedule{" +
                "id=" + id +
                ", animalId=" + animalId +
                ", feedingTime=" + feedingTime +
                ", foodType=" + foodType +
                ", completed=" + completed +
                '}';
    }
}