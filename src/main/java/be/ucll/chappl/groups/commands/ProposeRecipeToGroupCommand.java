package be.ucll.chappl.groups.commands;

import java.util.UUID;

public record ProposeRecipeToGroupCommand(UUID groupId, UUID recipeId) {}
