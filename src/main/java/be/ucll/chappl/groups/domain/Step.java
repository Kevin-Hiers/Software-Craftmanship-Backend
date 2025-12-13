package be.ucll.chappl.groups.domain;

import java.util.Objects;

public record Step(Ingredient ingredient, String instruction) {
    public Step {
        Objects.requireNonNull(ingredient, "ingredient is required");
        if (instruction == null || instruction.isBlank()) throw new IllegalArgumentException("instruction is required");
        instruction = instruction.trim();
    }
}
