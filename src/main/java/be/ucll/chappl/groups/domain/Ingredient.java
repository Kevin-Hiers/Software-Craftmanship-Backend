package be.ucll.chappl.groups.domain;

import java.util.Objects;

public record Ingredient(String name, Measurement measurement) {
    public Ingredient {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("ingredient name is required");
        name = name.trim();
        Objects.requireNonNull(measurement, "measurement is required");
    }
}
