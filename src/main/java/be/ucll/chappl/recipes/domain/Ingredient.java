package be.ucll.chappl.recipes.domain;

public record Ingredient(String name, Measurement measurement) {
    public Ingredient {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("ingredient name required");
        name = name.trim();
        if (measurement == null) throw new IllegalArgumentException("measurement required");
    }
}
