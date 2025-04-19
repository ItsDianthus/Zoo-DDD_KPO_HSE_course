package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.HealthStatus;
import com.proj.KPO_DZ_2.domain.model.enums.Sex;
import com.proj.KPO_DZ_2.domain.valueobjects.AnimalName;
import com.proj.KPO_DZ_2.domain.valueobjects.FavoriteFood;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Animal {

    private final UUID id;
    private String species;
    private AnimalName name;
    private LocalDate birthDate;
    private Sex sex;
    private FavoriteFood favoriteFood;
    private HealthStatus status;
    private UUID enclosureId;

    public Animal(UUID id,
                  String species,
                  AnimalName name,
                  LocalDate birthDate,
                  Sex sex,
                  FavoriteFood favoriteFood,
                  HealthStatus status,
                  UUID enclosureId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.species = Objects.requireNonNull(species, "species must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.sex = Objects.requireNonNull(sex, "sex must not be null");
        this.favoriteFood = Objects.requireNonNull(favoriteFood, "favoriteFood must not be null");
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.enclosureId = Objects.requireNonNull(enclosureId, "enclosureId must not be null");
    }

    public UUID getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public AnimalName getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public FavoriteFood getFavoriteFood() {
        return favoriteFood;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public UUID getEnclosureId() {
        return enclosureId;
    }

    public void feed() {
        // business logic, e.g. mark last feeding timestamp, publish FeedingTimeEvent
    }

    public void treat() {
        this.status = HealthStatus.HEALTHY;
    }

    public void moveTo(UUID newEnclosureId) {
        Objects.requireNonNull(newEnclosureId, "newEnclosureId must not be null");
        if (this.enclosureId.equals(newEnclosureId)) {
            throw new IllegalArgumentException("Нельзя переместить животное в тот же вольер");
        }
        this.enclosureId = newEnclosureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", name=" + name +
                ", birthDate=" + birthDate +
                ", sex=" + sex +
                ", favoriteFood=" + favoriteFood +
                ", status=" + status +
                ", enclosureId=" + enclosureId +
                '}';
    }
}