package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.commands.*;
import be.ucll.chappl.groups.domain.Group;
import be.ucll.chappl.groups.infrastructure.GroupRepository;
import be.ucll.chappl.recipes.infrastructure.RecipeRepository;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GroupCommandHandler {
    private final GroupRepository groups;
    private final UserRepository users;
    private final RecipeRepository recipes;

    public GroupCommandHandler(GroupRepository groups, UserRepository users, RecipeRepository recipes) {
        this.groups = groups;
        this.users = users;
        this.recipes = recipes;
    }

    public GroupInformation handle(CreateGroupCommand cmd) {
        var g = Group.create();
        groups.save(g);
        return GroupMapper.toInformation(g, users, recipes);
    }

    public GroupInformation handle(AddUserToGroupCommand cmd) {
        var g = groups.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));
        users.findById(cmd.userId()).orElseThrow(() -> new NoSuchElementException("user not found"));
        g.addUser(cmd.userId());
        groups.save(g);
        return GroupMapper.toInformation(g, users, recipes);
    }

    public GroupInformation handle(ProposeRecipeToGroupCommand cmd) {
        var g = groups.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));
        recipes.findById(cmd.recipeId()).orElseThrow(() -> new NoSuchElementException("recipe not found"));
        g.proposeRecipe(cmd.recipeId());
        groups.save(g);
        return GroupMapper.toInformation(g, users, recipes);
    }

    public GroupInformation handle(VoteForProposalCommand cmd) {
        var g = groups.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));
        g.voteFor(cmd.proposalId());
        groups.save(g);
        return GroupMapper.toInformation(g, users, recipes);
    }
}
