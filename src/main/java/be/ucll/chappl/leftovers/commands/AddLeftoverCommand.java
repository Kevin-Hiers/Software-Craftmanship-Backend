package be.ucll.chappl.leftovers.commands;

import java.util.UUID;

public record AddLeftoverCommand(
        UUID userId,
        String ingredientName,
        String amount,
        String unit,
        String expirationDate // ISO yyyy-MM-dd
) {}
