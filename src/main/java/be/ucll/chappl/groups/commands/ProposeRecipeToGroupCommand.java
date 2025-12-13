package be.ucll.chappl.groups.commands;

import java.util.List;
import java.util.UUID;

public record ProposeRecipeToGroupCommand(
        UUID groupId,
        String recipeName,
        boolean isPublic,
        List<StepInput> steps
) {
    public record StepInput(String ingredientName, String instruction, String unit, String amount) {}
}
