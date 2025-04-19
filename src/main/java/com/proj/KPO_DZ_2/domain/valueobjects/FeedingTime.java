package com.proj.KPO_DZ_2.domain.valueobjects;

import java.time.LocalTime;
import java.util.Objects;

public final class FeedingTime {

    private final LocalTime time;

    private FeedingTime(LocalTime time) {
        this.time = Objects.requireNonNull(time, "Время кормления не может быть null");
        validate();
    }

    public static FeedingTime of(LocalTime time) {
        return new FeedingTime(time);
    }

    private void validate() {
        // кормление только в интервале 06:00–20:00
        if (time.isBefore(LocalTime.of(6, 0)) || time.isAfter(LocalTime.of(20, 0))) {
            throw new IllegalArgumentException("Кормление возможно только с 06:00 до 20:00");
        }
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedingTime)) return false;
        FeedingTime that = (FeedingTime) o;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Override
    public String toString() {
        return time.toString();
    }
}






