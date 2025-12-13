package be.ucll.chappl.groups.domain;

import java.util.*;

public class Group {
    private final UUID id;
    private final Set<UUID> userIds;
    private final List<Proposal> proposals;

    public Group(UUID id) {
        if (id == null) throw new IllegalArgumentException("id required");
        this.id = id;
        this.userIds = new HashSet<>();
        this.proposals = new ArrayList<>();
    }

    public static Group create() {
        return new Group(UUID.randomUUID());
    }

    public UUID getId() { return id; }
    public Set<UUID> getUserIds() { return Set.copyOf(userIds); }
    public List<Proposal> getProposals() { return List.copyOf(proposals); }

    public void addUser(UUID userId) {
        if (userId == null) throw new IllegalArgumentException("userId required");
        if (!userIds.add(userId)) throw new IllegalStateException("user already in group");
    }

    public Proposal proposeRecipe(UUID recipeId) {
        if (recipeId == null) throw new IllegalArgumentException("recipeId required");
        var p = Proposal.forRecipe(recipeId);
        proposals.add(p);
        return p;
    }

    public Proposal voteFor(UUID proposalId) {
        for (int i = 0; i < proposals.size(); i++) {
            var p = proposals.get(i);
            if (p.id().equals(proposalId)) {
                var updated = p.upvote();
                proposals.set(i, updated);
                return updated;
            }
        }
        throw new NoSuchElementException("proposal not found");
    }
}
