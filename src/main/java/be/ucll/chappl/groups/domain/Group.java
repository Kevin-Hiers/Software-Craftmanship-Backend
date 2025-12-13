package be.ucll.chappl.groups.domain;

import java.util.*;

public class Group {
    private final UUID id;
    private final List<User> users;
    private final List<Proposal> proposals;

    public Group(UUID id) {
        this.id = Objects.requireNonNull(id, "id is required");
        this.users = new ArrayList<>();
        this.proposals = new ArrayList<>();
    }

    public static Group create() {
        return new Group(UUID.randomUUID());
    }

    public UUID getId() { return id; }
    public List<User> getUsers() { return List.copyOf(users); }
    public List<Proposal> getProposals() { return List.copyOf(proposals); }

    public void addUser(User user) {
        Objects.requireNonNull(user, "user is required");
        boolean exists = users.stream().anyMatch(u ->
                u.getUsername().equalsIgnoreCase(user.getUsername())
                        || u.getEmail().equalsIgnoreCase(user.getEmail())
        );
        if (exists) throw new IllegalStateException("user already exists in group");
        users.add(user);
    }

    public Proposal proposeRecipe(Recipe recipe) {
        Objects.requireNonNull(recipe, "recipe is required");
        var proposal = Proposal.of(recipe);
        proposals.add(proposal);
        return proposal;
    }

    public Proposal voteFor(UUID proposalId) {
        Objects.requireNonNull(proposalId, "proposalId is required");
        for (int i = 0; i < proposals.size(); i++) {
            Proposal p = proposals.get(i);
            if (p.id().equals(proposalId)) {
                Proposal updated = p.upvote();
                proposals.set(i, updated);
                return updated;
            }
        }
        throw new NoSuchElementException("proposal not found: " + proposalId);
    }
}
