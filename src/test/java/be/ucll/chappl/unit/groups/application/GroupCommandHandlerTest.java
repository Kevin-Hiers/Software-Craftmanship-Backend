package be.ucll.chappl.unit.groups.application;

import be.ucll.chappl.groups.application.GroupCommandHandler;
import be.ucll.chappl.groups.commands.*;
import be.ucll.chappl.groups.infrastructure.InMemoryGroupRepository;
import be.ucll.chappl.recipes.application.RecipeCommandHandler;
import be.ucll.chappl.recipes.commands.CreateRecipeCommand;
import be.ucll.chappl.recipes.infrastructure.InMemoryRecipeRepository;
import be.ucll.chappl.users.application.UserCommandHandler;
import be.ucll.chappl.users.commands.RegisterUserCommand;
import be.ucll.chappl.users.infrastructure.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupCommandHandlerTest {

    @Test
    void givenExistingUserAndRecipe_whenAddedAndProposed_thenGroupContainsExpandedInformation() {
        var usersRepo = new InMemoryUserRepository();
        var recipesRepo = new InMemoryRecipeRepository();
        var groupsRepo = new InMemoryGroupRepository();

        var userCommands = new UserCommandHandler(usersRepo);
        var recipeCommands = new RecipeCommandHandler(recipesRepo);

        var groupCommands = new GroupCommandHandler(groupsRepo, usersRepo, recipesRepo);

        var user = userCommands.handle(new RegisterUserCommand("stefanie", "stefanie@ucll.be", "pw"));
        var recipe = recipeCommands.handle(new CreateRecipeCommand(
                "Salad",
                false,
                List.of(new CreateRecipeCommand.StepInput("Lettuce", "1", "pc", "Wash"))
        ));

        var group = groupCommands.handle(new CreateGroupCommand());

        var afterAdd = groupCommands.handle(new AddUserToGroupCommand(group.id(), user.id()));
        assertEquals(1, afterAdd.users().size());
        assertEquals(user.id(), afterAdd.users().get(0).id());

        var afterPropose = groupCommands.handle(new ProposeRecipeToGroupCommand(group.id(), recipe.id()));
        assertEquals(1, afterPropose.proposals().size());
        assertEquals(recipe.id(), afterPropose.proposals().get(0).recipeId());
        assertEquals("Salad", afterPropose.proposals().get(0).recipeName());
        assertEquals(0, afterPropose.proposals().get(0).votes());
    }

    @Test
    void givenExistingProposal_whenVoting_thenVotesIncreaseInDto() {
        var usersRepo = new InMemoryUserRepository();
        var recipesRepo = new InMemoryRecipeRepository();
        var groupsRepo = new InMemoryGroupRepository();

        var userCommands = new UserCommandHandler(usersRepo);
        var recipeCommands = new RecipeCommandHandler(recipesRepo);

        var groupCommands = new GroupCommandHandler(groupsRepo, usersRepo, recipesRepo);

        var user = userCommands.handle(new RegisterUserCommand("stefanie", "stefanie@ucll.be", "pw"));
        var recipe = recipeCommands.handle(new CreateRecipeCommand(
                "Soup",
                true,
                List.of(new CreateRecipeCommand.StepInput("Water", "1", "l", "Boil"))
        ));

        var group = groupCommands.handle(new CreateGroupCommand());
        groupCommands.handle(new AddUserToGroupCommand(group.id(), user.id()));
        var afterPropose = groupCommands.handle(new ProposeRecipeToGroupCommand(group.id(), recipe.id()));

        var proposalId = afterPropose.proposals().get(0).id();
        var afterVote = groupCommands.handle(new VoteForProposalCommand(group.id(), proposalId));

        assertEquals(1, afterVote.proposals().size());
        assertEquals(1, afterVote.proposals().get(0).votes());
    }
}