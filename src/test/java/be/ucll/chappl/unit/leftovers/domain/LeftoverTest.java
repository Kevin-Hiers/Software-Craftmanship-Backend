package be.ucll.chappl.unit.leftovers.domain;

import be.ucll.chappl.leftovers.domain.Leftover;
import be.ucll.chappl.recipes.domain.Ingredient;
import be.ucll.chappl.recipes.domain.Measurement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LeftoverTest {

    @Test
    void givenValidLeftover_whenCreated_thenHasFields() {
        var userId = UUID.randomUUID();
        var ingredient = new Ingredient("Rice", new Measurement(new BigDecimal("200"), "g"));
        var exp = LocalDate.of(2026, 1, 1);

        var l = Leftover.create(userId, ingredient, exp);

        assertNotNull(l.getId());
        assertEquals(userId, l.getUserId());
        assertEquals("Rice", l.getIngredient().name());
        assertEquals(exp, l.getExpirationDate());
    }

    @Test
    void givenNullUserId_whenCreatingLeftover_thenThrows() {
        var ingredient = new Ingredient("Rice", new Measurement(new BigDecimal("1"), "pc"));
        var exp = LocalDate.of(2026, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> Leftover.create(null, ingredient, exp));
    }

    @Test
    void givenNullIngredient_whenCreatingLeftover_thenThrows() {
        var exp = LocalDate.of(2026, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> Leftover.create(UUID.randomUUID(), null, exp));
    }

    @Test
    void givenNullExpirationDate_whenCreatingLeftover_thenThrows() {
        var ingredient = new Ingredient("Rice", new Measurement(new BigDecimal("1"), "pc"));

        assertThrows(IllegalArgumentException.class, () -> Leftover.create(UUID.randomUUID(), ingredient, null));
    }
}