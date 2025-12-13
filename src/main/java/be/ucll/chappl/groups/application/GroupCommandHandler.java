package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.commands.*;
import be.ucll.chappl.groups.domain.*;
import be.ucll.chappl.groups.infrastructure.GroupRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupCommandHandler {
    private final GroupRepository groupRepository;

    public GroupCommandHandler(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupInformation handle(CreateGroupCommand cmd) {
        var group = Group.create();
        groupRepository.save(group);
        return GroupInformation.from(group);
    }

    public GroupInformation handle(AddUserToGroupCommand cmd) {
        var group = groupRepository.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));
        group.addUser(User.create(cmd.username(), cmd.email(), cmd.password()));
        groupRepository.save(group);
        return GroupInformation.from(group);
    }

    public GroupInformation handle(ProposeRecipeToGroupCommand cmd) {
        var group = groupRepository.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));

        List<Step> steps = cmd.steps().stream().map(s -> {
            var measurement = new Measurement(new BigDecimal(s.amount()), s.unit());
            var ingredient = new Ingredient(s.ingredientName(), measurement);
            return new Step(ingredient, s.instruction());
        }).toList();

        var recipe = Recipe.create(cmd.recipeName(), cmd.isPublic(), steps);
        group.proposeRecipe(recipe);

        groupRepository.save(group);
        return GroupInformation.from(group);
    }

    public GroupInformation handle(VoteForProposalCommand cmd) {
        var group = groupRepository.findById(cmd.groupId()).orElseThrow(() -> new NoSuchElementException("group not found"));
        group.voteFor(cmd.proposalId());
        groupRepository.save(group);
        return GroupInformation.from(group);
    }
}
