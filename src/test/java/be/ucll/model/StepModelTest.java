package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StepModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidStep_whenValidate_thenNoViolations() {
        Ingredient ingredient = new Ingredient("Tomato");
        Step step = new Step(ingredient, "Chop the tomato.");

        Set<ConstraintViolation<Step>> violations = validator.validate(step);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenNullIngredient_whenValidate_thenIngredientViolation() {
        Step step = new Step(null, "Do something.");

        Set<ConstraintViolation<Step>> violations = validator.validate(step);

        assertEquals(1, violations.size());
        ConstraintViolation<Step> v = violations.iterator().next();
        assertEquals("ingredient", v.getPropertyPath().toString());
        assertEquals("Ingredient cannot be null", v.getMessage());
    }

    @Test
    void givenBlankInstruction_whenValidate_thenInstructionViolation() {
        Ingredient ingredient = new Ingredient("Tomato");
        Step step = new Step(ingredient, "   ");

        Set<ConstraintViolation<Step>> violations = validator.validate(step);

        assertEquals(1, violations.size());
        ConstraintViolation<Step> v = violations.iterator().next();
        assertEquals("instruction", v.getPropertyPath().toString());
        assertEquals("Instruction cannot be empty", v.getMessage());
    }
}
