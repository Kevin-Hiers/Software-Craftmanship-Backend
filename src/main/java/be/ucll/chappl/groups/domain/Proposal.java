package be.ucll.chappl.groups.domain;

import java.util.UUID;

public record Proposal(UUID id, UUID recipeId, int votes) {
    public Proposal {
        if (id == null) throw new IllegalArgumentException("id required");
        if (recipeId == null) throw new IllegalArgumentException("recipeId required");
        if (votes < 0) throw new IllegalArgumentException("votes cannot be negative");
    }

    public static Proposal forRecipe(UUID recipeId) {
        return new Proposal(UUID.randomUUID(), recipeId, 0);
    }

    public Proposal upvote() {
        return new Proposal(id, recipeId, votes + 1);
    }
}
