package be.ucll.chappl.groups.commands;

import java.util.UUID;

public record VoteForProposalCommand(UUID groupId, UUID proposalId) {}
