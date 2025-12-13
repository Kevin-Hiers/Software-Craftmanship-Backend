package be.ucll.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidIngredient_whenValidate_thenNoViolations() {
        Ingredient ingredient = new Ingredient(
                "Tomato",
                new Measurement(new BigDecimal("2.5"), "pcs")
        );

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenBlankIngredientName_whenValidate_thenNameViolation() {
        // given
        Ingredient ingredient = new Ingredient(
                "   ",
                new Measurement(new BigDecimal("1"), "pcs")
        );

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertEquals(1, violations.size());
        ConstraintViolation<Ingredient> v = violations.iterator().next();
        assertEquals("name", v.getPropertyPath().toString());
        assertEquals("Ingredient name cannot be empty", v.getMessage());
    }

    @Test
    void givenNullMeasurement_whenValidate_thenMeasurementViolation() {
        Ingredient ingredient = new Ingredient("Tomato", null);

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertEquals(1, violations.size());
        ConstraintViolation<Ingredient> v = violations.iterator().next();
        assertEquals("measurement", v.getPropertyPath().toString());
        assertEquals("Measurement cannot be null", v.getMessage());
    }

    @Test
    void givenBlankUnit_whenValidate_thenUnitViolation() {
        Ingredient ingredient = new Ingredient(
                "Tomato",
                new Measurement(new BigDecimal("1"), "   ")
        );

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertEquals(1, violations.size());
        ConstraintViolation<Ingredient> v = violations.iterator().next();
        assertEquals("measurement.unit", v.getPropertyPath().toString());
        assertEquals("Unit cannot be empty", v.getMessage());
    }

    @Test
    void givenNonPositiveAmount_whenValidate_thenAmountViolation() {
        Ingredient ingredient = new Ingredient(
                "Tomato",
                new Measurement(new BigDecimal("0"), "pcs")
        );

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertEquals(1, violations.size());
        ConstraintViolation<Ingredient> v = violations.iterator().next();
        assertEquals("measurement.amount", v.getPropertyPath().toString());
        assertEquals("Amount must be greater than 0", v.getMessage());
    }
}
