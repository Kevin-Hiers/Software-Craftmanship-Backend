package be.ucll.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record Step(
        @NotNull(message = "Ingredient cannot be null")
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "ingredient_id")
        Ingredient ingredient,

        @NotBlank(message = "Instruction cannot be empty")
        String instruction
) {}
