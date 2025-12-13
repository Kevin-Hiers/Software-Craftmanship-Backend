package be.ucll.chappl.groups.controllers;

import be.ucll.chappl.groups.application.GroupCommandHandler;
import be.ucll.chappl.groups.application.GroupInformation;
import be.ucll.chappl.groups.application.GroupQueryHandler;
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

    @PostMapping("/{groupId}/users")
    public GroupInformation addUser(@PathVariable UUID groupId, @RequestBody AddUserRequest body) {
        return commands.handle(new AddUserToGroupCommand(groupId, body.username(), body.email(), body.password()));
    }

    @PostMapping("/{groupId}/proposals")
    public GroupInformation propose(@PathVariable UUID groupId, @RequestBody ProposeRecipeToGroupCommand body) {
        // body already contains groupId in the record, but we enforce path groupId:
        return commands.handle(new ProposeRecipeToGroupCommand(groupId, body.recipeName(), body.isPublic(), body.steps()));
    }

    @PostMapping("/{groupId}/proposals/{proposalId}/vote")
    public GroupInformation vote(@PathVariable UUID groupId, @PathVariable UUID proposalId) {
        return commands.handle(new VoteForProposalCommand(groupId, proposalId));
    }

    public record AddUserRequest(String username, String email, String password) {}
}
