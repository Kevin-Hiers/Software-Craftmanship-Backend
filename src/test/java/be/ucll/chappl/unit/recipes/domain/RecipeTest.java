package be.ucll.chappl.unit.recipes.domain;

import be.ucll.chappl.recipes.domain.Ingredient;
import be.ucll.chappl.recipes.domain.Measurement;
import be.ucll.chappl.recipes.domain.Recipe;
import be.ucll.chappl.recipes.domain.Step;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void givenValidRecipe_whenCreated_thenHasIdAndSteps() {
        var steps = List.of(
                new Step(new Ingredient("Pasta", new Measurement(new BigDecimal("200"), "g")), "Boil"),
                new Step(new Ingredient("Sauce", new Measurement(new BigDecimal("150"), "ml")), "Add")
        );

        var recipe = Recipe.create("Pasta", true, steps);

        assertNotNull(recipe.getId());
        assertEquals("Pasta", recipe.getName());
        assertTrue(recipe.isPublic());
        assertEquals(2, recipe.getSteps().size());
    }

    @Test
    void givenBlankName_whenCreatingRecipe_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> Recipe.create(" ", true, List.of()));
    }

    @Test
    void givenNullSteps_whenCreatingRecipe_thenRecipeHasZeroSteps() {
        var recipe = Recipe.create("Soup", false, null);

        assertEquals(0, recipe.getSteps().size());
    }
}