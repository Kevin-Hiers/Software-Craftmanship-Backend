package be.ucll.chappl.groups.application;

import java.util.UUID;

public record ProposalInformation(UUID id, UUID recipeId, String recipeName, boolean isPublic, int votes) {}
