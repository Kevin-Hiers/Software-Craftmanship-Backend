package be.ucll.chappl.groups.commands;

import java.util.UUID;

public record AddUserToGroupCommand(UUID groupId, UUID userId) {}
