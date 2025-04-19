package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.EnclosureType;
import com.proj.KPO_DZ_2.domain.valueobjects.EnclosureCapacity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Enclosure {

    private final UUID id;
    private EnclosureType type;
    private double size;
    private int currentAnimals;
    private EnclosureCapacity capacity;
    private LocalDateTime lastCleanedAt;

    public Enclosure(UUID id,
                     EnclosureType type,
                     double size,
                     EnclosureCapacity capacity) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.type = Objects.requireNonNull(type, "type must not be null");
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        this.size = size;
        this.capacity = Objects.requireNonNull(capacity, "capacity must not be null");
        this.currentAnimals = 0;
        this.lastCleanedAt = null;
    }

    public UUID getId() {
        return id;
    }

    public EnclosureType getType() {
        return type;
    }

    public double getSize() {
        return size;
    }

    public int getCurrentAnimals() {
        return currentAnimals;
    }

    public EnclosureCapacity getCapacity() {
        return capacity;
    }

    public LocalDateTime getLastCleanedAt() {
        return lastCleanedAt;
    }

    /** Добавить животное, проверив вместимость */
    public void addAnimal() {
        if (currentAnimals >= capacity.getValue()) {
            throw new IllegalStateException("Вольер полон");
        }
        currentAnimals++;
    }

    /** Убрать животное */
    public void removeAnimal() {
        if (currentAnimals <= 0) {
            throw new IllegalStateException("В вольере нет животных");
        }
        currentAnimals--;
    }

    /** Провести уборку */
    public void clean() {
        this.lastCleanedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enclosure that = (Enclosure) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "id=" + id +
                ", type=" + type +
                ", size=" + size +
                ", currentAnimals=" + currentAnimals +
                ", capacity=" + capacity +
                ", lastCleanedAt=" + lastCleanedAt +
                '}';
    }
}