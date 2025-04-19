package com.proj.KPO_DZ_2.domain.model;

import com.proj.KPO_DZ_2.domain.model.enums.HealthStatus;
import com.proj.KPO_DZ_2.domain.model.enums.Sex;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Animal {

    private final UUID id;
    private String species;
    private String name;
    private LocalDate birthDate;
    private Sex sex;
    private String favoriteFood;
    private HealthStatus status;
    private UUID enclosureId;

    public Animal(UUID id,
                  String species,
                  String name,
                  LocalDate birthDate,
                  Sex sex,
                  String favoriteFood,
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

    public UUID getId() { return id; }
    public String getSpecies() { return species; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public Sex getSex() { return sex; }
    public String getFavoriteFood() { return favoriteFood; }
    public HealthStatus getStatus() { return status; }
    public UUID getEnclosureId() { return enclosureId; }


    public void feed() {
        // мб отметка последнего кормления и генерация события
    }

    public void treat() {
        this.status = HealthStatus.HEALTHY;
    }

    public void moveTo(UUID newEnclosureId) {
        if (this.enclosureId.equals(newEnclosureId)) {
            throw new IllegalArgumentException("Нельзя переместить животное в тот же вольер");
        }
        this.enclosureId = Objects.requireNonNull(newEnclosureId, "newEnclosureId must not be null");
        // публикация события AnimalMovedEvent в сервисе
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}