package be.ucll.chappl.groups.commands;

import java.util.UUID;

public record AddUserToGroupCommand(UUID groupId, String username, String email, String password) {}
