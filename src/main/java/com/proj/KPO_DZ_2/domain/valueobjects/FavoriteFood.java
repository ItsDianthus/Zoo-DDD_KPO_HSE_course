package com.proj.KPO_DZ_2.domain.valueobjects;

import java.util.Objects;

public final class FavoriteFood {

    private final String value;

    private FavoriteFood(String value) {
        this.value = Objects.requireNonNull(value, "FavoriteFood value must not be null");
        validate();
    }

    public static FavoriteFood of(String value) {
        return new FavoriteFood(value);
    }

    private void validate() {
        if (value.isBlank()) {
            throw new IllegalArgumentException("Любимая еда не может быть пустой");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("Название еды слишком длинное (макс. 100 символов)");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteFood)) return false;
        FavoriteFood that = (FavoriteFood) o;
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
