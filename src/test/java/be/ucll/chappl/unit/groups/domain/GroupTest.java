package be.ucll.chappl.unit.groups.domain;

import be.ucll.chappl.groups.domain.Group;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void givenNewGroup_whenCreated_thenHasIdAndEmptyCollections() {
        var group = Group.create();

        assertNotNull(group.getId());
        assertEquals(0, group.getUserIds().size());
        assertEquals(0, group.getProposals().size());
    }

    @Test
    void givenGroup_whenAddingUser_thenUserIdIsPresent() {
        var group = Group.create();
        var userId = UUID.randomUUID();

        group.addUser(userId);

        assertTrue(group.getUserIds().contains(userId));
    }

    @Test
    void givenGroupWithUser_whenAddingSameUserAgain_thenThrows() {
        var group = Group.create();
        var userId = UUID.randomUUID();

        group.addUser(userId);

        assertThrows(IllegalStateException.class, () -> group.addUser(userId));
    }

    @Test
    void givenGroup_whenProposingRecipe_thenProposalIsAdded() {
        var group = Group.create();
        var recipeId = UUID.randomUUID();

        var proposal = group.proposeRecipe(recipeId);

        assertEquals(1, group.getProposals().size());
        assertEquals(proposal.id(), group.getProposals().get(0).id());
    }

    @Test
    void givenGroupWithProposal_whenVoting_thenVotesIncrease() {
        var group = Group.create();
        var recipeId = UUID.randomUUID();
        var proposal = group.proposeRecipe(recipeId);

        var updated = group.voteFor(proposal.id());

        assertEquals(1, updated.votes());
    }

    @Test
    void givenGroupWithoutProposal_whenVoting_thenThrows() {
        var group = Group.create();

        assertThrows(java.util.NoSuchElementException.class, () -> group.voteFor(UUID.randomUUID()));
    }
}