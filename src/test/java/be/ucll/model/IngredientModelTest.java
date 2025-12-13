package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidIngredient_whenValidate_thenNoViolations() {
        Ingredient ingredient = new Ingredient("Tomato");

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenBlankIngredientName_whenValidate_thenNameViolation() {
        Ingredient ingredient = new Ingredient("   ");

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertEquals(1, violations.size());
        ConstraintViolation<Ingredient> v = violations.iterator().next();
        assertEquals("name", v.getPropertyPath().toString());
        assertEquals("Ingredient name cannot be empty", v.getMessage());
    }
}
