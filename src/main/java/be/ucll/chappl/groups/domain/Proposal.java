package be.ucll.chappl.groups.domain;

import java.util.Objects;
import java.util.UUID;

public record Proposal(UUID id, Recipe recipe, int votes) {
    public Proposal {
        Objects.requireNonNull(id, "id is required");
        Objects.requireNonNull(recipe, "recipe is required");
        if (votes < 0) throw new IllegalArgumentException("votes cannot be negative");
    }

    public static Proposal of(Recipe recipe) {
        return new Proposal(UUID.randomUUID(), recipe, 0);
    }

    public Proposal upvote() {
        return new Proposal(id, recipe, votes + 1);
    }
}
