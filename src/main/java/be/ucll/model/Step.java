package be.ucll.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Step(
        @NotNull(message = "Ingredient cannot be null")
        @Valid
        @Embedded
        Ingredient ingredient,

        @NotBlank(message = "Instruction cannot be empty")
        String instruction
) {}
