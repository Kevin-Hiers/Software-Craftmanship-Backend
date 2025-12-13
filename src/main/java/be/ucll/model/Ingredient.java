package be.ucll.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Ingredient(
        @NotBlank(message = "Ingredient name cannot be empty")
        String name,

        @NotNull(message = "Measurement cannot be null")
        @Valid
        @Embedded
        Measurement measurement
) {}
