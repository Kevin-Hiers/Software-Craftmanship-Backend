package be.ucll.chappl.unit.recipes.domain;

import be.ucll.chappl.recipes.domain.Ingredient;
import be.ucll.chappl.recipes.domain.Measurement;
import be.ucll.chappl.recipes.domain.Step;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StepTest {

    @Test
    void givenValidStep_whenCreated_thenHasIngredientAndInstruction() {
        var m = new Measurement(new BigDecimal("1"), "pc");
        var ing = new Ingredient("Egg", m);
        var step = new Step(ing, "Crack egg");

        assertEquals(ing, step.ingredient());
        assertEquals("Crack egg", step.instruction());
    }

    @Test
    void givenNullIngredient_whenCreatingStep_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Step(null, "Do thing"));
    }

    @Test
    void givenBlankInstruction_whenCreatingStep_thenThrows() {
        var m = new Measurement(new BigDecimal("1"), "pc");
        var ing = new Ingredient("Egg", m);
        assertThrows(IllegalArgumentException.class, () -> new Step(ing, " "));
    }
}
