package be.ucll.chappl.recipes.commands;

import java.util.List;

public record CreateRecipeCommand(String name, boolean isPublic, List<StepInput> steps) {
    public record StepInput(String ingredientName, String amount, String unit, String instruction) {}
}
