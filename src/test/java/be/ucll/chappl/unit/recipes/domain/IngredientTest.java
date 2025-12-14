package be.ucll.chappl.unit.recipes.domain;

import be.ucll.chappl.recipes.domain.Ingredient;
import be.ucll.chappl.recipes.domain.Measurement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void givenValidIngredient_whenCreated_thenHasNameAndMeasurement() {
        var m = new Measurement(new BigDecimal("200"), "g");
        var ing = new Ingredient("Rice", m);

        assertEquals("Rice", ing.name());
        assertEquals(m, ing.measurement());
    }

    @Test
    void givenBlankName_whenCreatingIngredient_thenThrows() {
        var m = new Measurement(new BigDecimal("1"), "pc");
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(" ", m));
    }

    @Test
    void givenNullMeasurement_whenCreatingIngredient_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("Rice", null));
    }
}
