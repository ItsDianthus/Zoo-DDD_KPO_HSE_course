package com.proj.KPO_DZ_2.domain.valueobjects;

import java.util.Objects;

public final class EnclosureCapacity {

    private final int value;

    private EnclosureCapacity(int value) {
        this.value = value;
        validate();
    }

    public static EnclosureCapacity of(int value) {
        return new EnclosureCapacity(value);
    }

    private void validate() {
        if (value <= 0) {
            throw new IllegalArgumentException("Вместимость вольера должна быть положительным числом");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnclosureCapacity)) return false;
        EnclosureCapacity that = (EnclosureCapacity) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}







