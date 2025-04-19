package com.proj.KPO_DZ_2.domain.valueobjects;


import java.util.Objects;

public final class AnimalName {

    private final String value;

    private AnimalName(String value) {
        this.value = value;
        validate();
    }

    public static AnimalName of(String value) {
        return new AnimalName(value);
    }

    private void validate() {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Имя животного не может быть пустым");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("Имя животного слишком длинное (макс. 50 символов)");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimalName)) return false;
        AnimalName that = (AnimalName) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}