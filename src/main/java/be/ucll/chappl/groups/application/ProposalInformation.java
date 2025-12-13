package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.domain.Proposal;

import java.util.UUID;

public record ProposalInformation(UUID id, UUID recipeId, String recipeName, boolean isPublic, int votes) {
    static ProposalInformation from(Proposal p) {
        return new ProposalInformation(
                p.id(),
                p.recipe().getId(),
                p.recipe().getName(),
                p.recipe().isPublic(),
                p.votes()
        );
    }
}
