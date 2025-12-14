package be.ucll.chappl.unit.recipes.domain;


import be.ucll.chappl.recipes.domain.Measurement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementTest {

    @Test
    void givenValidMeasurement_whenCreated_thenHasAmountAndUnit() {
        var m = new Measurement(new BigDecimal("1.5"), "kg");

        assertEquals(new BigDecimal("1.5"), m.amount());
        assertEquals("kg", m.unit());
    }

    @Test
    void givenZeroAmount_whenCreatingMeasurement_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Measurement(BigDecimal.ZERO, "g"));
    }

    @Test
    void givenNegativeAmount_whenCreatingMeasurement_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Measurement(new BigDecimal("-1"), "g"));
    }

    @Test
    void givenBlankUnit_whenCreatingMeasurement_thenThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Measurement(new BigDecimal("1"), " "));
    }
}