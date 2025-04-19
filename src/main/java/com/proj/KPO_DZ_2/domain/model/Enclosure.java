package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.EnclosureType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Enclosure {

    private final UUID id;
    private EnclosureType type;
    private double size;
    private int currentAnimals;
    private int capacity;
    private LocalDateTime lastCleanedAt;

    public Enclosure(UUID id, EnclosureType type, double size, int capacity) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
        if (size <= 0) throw new IllegalArgumentException("size must be positive");
        if (capacity < 1) throw new IllegalArgumentException("capacity must be at least 1");
        this.size = size;
        this.capacity = capacity;
        this.currentAnimals = 0;
        this.lastCleanedAt = null;
    }

    public UUID getId() { return id; }
    public EnclosureType getType() { return type; }
    public double getSize() { return size; }
    public int getCurrentAnimals() { return currentAnimals; }
    public int getCapacity() { return capacity; }
    public LocalDateTime getLastCleanedAt() { return lastCleanedAt; }

    public void addAnimal() {
        if (currentAnimals >= capacity) {
            throw new IllegalStateException("Вольер полон");
        }
        currentAnimals++;
    }

    public void removeAnimal() {
        if (currentAnimals <= 0) {
            throw new IllegalStateException("В вольере нет животных");
        }
        currentAnimals--;
    }

    public void clean() {
        this.lastCleanedAt = LocalDateTime.now();
    }
}