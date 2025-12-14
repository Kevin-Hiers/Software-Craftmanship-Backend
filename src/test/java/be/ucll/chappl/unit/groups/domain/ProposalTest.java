package be.ucll.chappl.unit.groups.domain;

import be.ucll.chappl.groups.domain.Proposal;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProposalTest {

    @Test
    void givenRecipeId_whenCreatingProposal_thenVotesStartAtZero() {
        var recipeId = UUID.randomUUID();

        var p = Proposal.forRecipe(recipeId);

        assertNotNull(p.id());
        assertEquals(recipeId, p.recipeId());
        assertEquals(0, p.votes());
    }

    @Test
    void givenProposal_whenUpvoted_thenVotesIncrease() {
        var p = Proposal.forRecipe(UUID.randomUUID());

        var updated = p.upvote();

        assertEquals(1, updated.votes());
        assertEquals(p.id(), updated.id());
        assertEquals(p.recipeId(), updated.recipeId());
    }

    @Test
    void givenNegativeVotes_whenCreatingProposal_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Proposal(UUID.randomUUID(), UUID.randomUUID(), -1));
    }
}