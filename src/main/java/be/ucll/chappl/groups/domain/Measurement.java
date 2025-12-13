package be.ucll.chappl.groups.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Measurement(BigDecimal amount, String unit) {
    public Measurement {
        Objects.requireNonNull(amount, "amount is required");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("amount must be > 0");
        if (unit == null || unit.isBlank()) throw new IllegalArgumentException("unit is required");
        unit = unit.trim();
    }
}
