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
class MeasurementModelTest {

    @Autowired
    private Validator validator;

    @Test
    void givenValidMeasurement_whenValidate_thenNoViolations() {
        Measurement measurement = new Measurement(new BigDecimal("1.5"), "g");

        Set<ConstraintViolation<Measurement>> violations = validator.validate(measurement);

        assertTrue(violations.isEmpty());
    }

    @Test
    void givenNullAmount_whenValidate_thenAmountViolation() {
        Measurement measurement = new Measurement(null, "g");

        Set<ConstraintViolation<Measurement>> violations = validator.validate(measurement);

        assertEquals(1, violations.size());
        ConstraintViolation<Measurement> v = violations.iterator().next();
        assertEquals("amount", v.getPropertyPath().toString());
        assertEquals("Amount cannot be null", v.getMessage());
    }

    @Test
    void givenZeroAmount_whenValidate_thenAmountViolation() {
        Measurement measurement = new Measurement(new BigDecimal("0"), "g");

        Set<ConstraintViolation<Measurement>> violations = validator.validate(measurement);

        assertEquals(1, violations.size());
        ConstraintViolation<Measurement> v = violations.iterator().next();
        assertEquals("amount", v.getPropertyPath().toString());
        assertEquals("Amount must be greater than 0", v.getMessage());
    }

    @Test
    void givenNegativeAmount_whenValidate_thenAmountViolation() {
        Measurement measurement = new Measurement(new BigDecimal("-2"), "g");

        Set<ConstraintViolation<Measurement>> violations = validator.validate(measurement);

        assertEquals(1, violations.size());
        ConstraintViolation<Measurement> v = violations.iterator().next();
        assertEquals("amount", v.getPropertyPath().toString());
        assertEquals("Amount must be greater than 0", v.getMessage());
    }

    @Test
    void givenBlankUnit_whenValidate_thenUnitViolation() {
        Measurement measurement = new Measurement(new BigDecimal("1"), "   ");

        Set<ConstraintViolation<Measurement>> violations = validator.validate(measurement);

        assertEquals(1, violations.size());
        ConstraintViolation<Measurement> v = violations.iterator().next();
        assertEquals("unit", v.getPropertyPath().toString());
        assertEquals("Unit cannot be empty", v.getMessage());
    }
}
