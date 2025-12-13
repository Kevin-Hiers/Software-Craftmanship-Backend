package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidRecipe_whenValidate_thenNoViolations() {
        Ingredient ingredient = new Ingredient("Tomato");
        Step step = new Step(ingredient, "Chop the tomato.");
        Recipe recipe = new Recipe("Pasta", true, List.of(step));

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenBlankName_whenValidate_thenNameViolation() {
        Recipe recipe = new Recipe("   ", true, List.of());

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        assertEquals(1, violations.size());
        ConstraintViolation<Recipe> v = violations.iterator().next();
        assertEquals("name", v.getPropertyPath().toString());
        assertEquals("Name cannot be empty", v.getMessage());
    }

    @Test
    void givenNullIsPublic_whenValidate_thenIsPublicViolation() {
        Recipe recipe = new Recipe();
        recipe.setName("Pasta");
        recipe.setIsPublic(null);
        recipe.setSteps(List.of());

        Set<ConstraintViolation<Recipe>> violations = validator.validate(recipe);

        assertEquals(1, violations.size());
        ConstraintViolation<Recipe> v = violations.iterator().next();
        assertEquals("isPublic", v.getPropertyPath().toString());
        assertEquals("IsPublic must be set", v.getMessage());
    }

    @Test
    void givenRecipe_whenDefaultConstructed_thenStepsIsNotNull() {
        Recipe recipe = new Recipe();

        List<Step> steps = recipe.getSteps();

        assertNotNull(steps);
        assertTrue(steps.isEmpty());
    }
}
