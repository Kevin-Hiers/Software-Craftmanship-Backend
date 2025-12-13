package be.ucll.chappl.groups.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Leftover {
    private final UUID id;
    private final Ingredient ingredient;
    private final LocalDate expirationDate;

    public Leftover(UUID id, Ingredient ingredient, LocalDate expirationDate) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.ingredient = Objects.requireNonNull(ingredient, "ingredient is required");
        this.expirationDate = Objects.requireNonNull(expirationDate, "expirationDate is required");
    }

    public static Leftover create(Ingredient ingredient, LocalDate expirationDate) {
        return new Leftover(UUID.randomUUID(), ingredient, expirationDate);
    }

    public UUID getId() { return id; }
    public Ingredient getIngredient() { return ingredient; }
    public LocalDate getExpirationDate() { return expirationDate; }
}
