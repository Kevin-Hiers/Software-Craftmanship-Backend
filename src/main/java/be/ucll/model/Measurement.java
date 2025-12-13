package be.ucll.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Embeddable
public record Measurement(
        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be greater than 0")
        BigDecimal amount,

        @NotBlank(message = "Unit cannot be empty")
        String unit
) {}
