package be.ucll.chappl.recipes.domain;

public record Step(Ingredient ingredient, String instruction) {
    public Step {
        if (ingredient == null) throw new IllegalArgumentException("ingredient required");
        if (instruction == null || instruction.isBlank()) throw new IllegalArgumentException("instruction required");
        instruction = instruction.trim();
    }
}
