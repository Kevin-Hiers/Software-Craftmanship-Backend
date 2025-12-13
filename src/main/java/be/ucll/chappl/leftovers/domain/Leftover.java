package be.ucll.chappl.leftovers.domain;

import be.ucll.chappl.recipes.domain.Ingredient;

import java.time.LocalDate;
import java.util.UUID;

public class Leftover {
    private final UUID id;
    private final UUID userId;
    private final Ingredient ingredient;
    private final LocalDate expirationDate;

    public Leftover(UUID id, UUID userId, Ingredient ingredient, LocalDate expirationDate) {
        if (id == null) throw new IllegalArgumentException("id required");
        if (userId == null) throw new IllegalArgumentException("userId required");
        if (ingredient == null) throw new IllegalArgumentException("ingredient required");
        if (expirationDate == null) throw new IllegalArgumentException("expirationDate required");
        this.id = id;
        this.userId = userId;
        this.ingredient = ingredient;
        this.expirationDate = expirationDate;
    }

    public static Leftover create(UUID userId, Ingredient ingredient, LocalDate expirationDate) {
        return new Leftover(UUID.randomUUID(), userId, ingredient, expirationDate);
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public Ingredient getIngredient() { return ingredient; }
    public LocalDate getExpirationDate() { return expirationDate; }
}
