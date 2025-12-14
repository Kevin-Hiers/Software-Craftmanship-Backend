package be.ucll.chappl.unit.recipes.application;

import be.ucll.chappl.recipes.application.RecipeCommandHandler;
import be.ucll.chappl.recipes.commands.CreateRecipeCommand;
import be.ucll.chappl.recipes.infrastructure.InMemoryRecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandHandlerTest {

    @Test
    void givenCreateRecipeCommand_whenHandled_thenRecipeIsCreatedAndStored() {
        var repo = new InMemoryRecipeRepository();
        var handler = new RecipeCommandHandler(repo);

        var steps = List.of(
                new CreateRecipeCommand.StepInput("Pasta", "200", "g", "Boil pasta"),
                new CreateRecipeCommand.StepInput("Sauce", "150", "ml", "Add sauce")
        );
        var cmd = new CreateRecipeCommand("Pasta arrabiata", true, steps);

        var created = handler.handle(cmd);

        assertNotNull(created.id());
        assertEquals("Pasta arrabiata", created.name());
        assertTrue(created.isPublic());

        var loaded = repo.findById(created.id()).orElseThrow();
        assertEquals("Pasta arrabiata", loaded.getName());
        assertTrue(loaded.isPublic());
        assertEquals(2, loaded.getSteps().size());
    }
}
