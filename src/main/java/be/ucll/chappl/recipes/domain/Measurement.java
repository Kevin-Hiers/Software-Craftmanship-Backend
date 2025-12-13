package be.ucll.chappl.recipes.domain;

import java.math.BigDecimal;

public record Measurement(BigDecimal amount, String unit) {
    public Measurement {
        if (amount == null || amount.signum() <= 0) throw new IllegalArgumentException("amount must be > 0");
        if (unit == null || unit.isBlank()) throw new IllegalArgumentException("unit required");
        unit = unit.trim();
    }
}
