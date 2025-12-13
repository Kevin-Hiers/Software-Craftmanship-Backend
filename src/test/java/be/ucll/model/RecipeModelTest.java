package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidRecipe_whenValidate_thenNoViolations() {
        Step step = new Step(
                new Ingredient("Tomato", new Measurement(new BigDecimal("2"), "pcs")),
                "Chop the tomatoes."
        );
        Recipe recipe = new Recipe("Pasta", true, List.of(step));

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        assertTrue(violations.isEmpty());
    }
}
