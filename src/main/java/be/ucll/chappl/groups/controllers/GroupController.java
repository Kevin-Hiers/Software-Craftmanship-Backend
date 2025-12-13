package be.ucll.chappl.groups.controllers;

import be.ucll.chappl.groups.application.*;
import be.ucll.chappl.groups.commands.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupCommandHandler commands;
    private final GroupQueryHandler queries;

    public GroupController(GroupCommandHandler commands, GroupQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping
    public GroupInformation create() {
        return commands.handle(new CreateGroupCommand());
    }

    @GetMapping("/{groupId}")
    public GroupInformation get(@PathVariable UUID groupId) {
        return queries.getById(groupId);
    }

    @PostMapping("/{groupId}/users/{userId}")
    public GroupInformation addUser(@PathVariable UUID groupId, @PathVariable UUID userId) {
        return commands.handle(new AddUserToGroupCommand(groupId, userId));
    }

    @PostMapping("/{groupId}/proposals/{recipeId}")
    public GroupInformation propose(@PathVariable UUID groupId, @PathVariable UUID recipeId) {
        return commands.handle(new ProposeRecipeToGroupCommand(groupId, recipeId));
    }

    @PostMapping("/{groupId}/proposals/{proposalId}/vote")
    public GroupInformation vote(@PathVariable UUID groupId, @PathVariable UUID proposalId) {
        return commands.handle(new VoteForProposalCommand(groupId, proposalId));
    }
}
