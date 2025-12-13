package be.ucll.chappl.leftovers.application;

import be.ucll.chappl.leftovers.domain.Leftover;

import java.time.LocalDate;
import java.util.UUID;

public record LeftoverInformation(
        UUID id,
        UUID userId,
        String ingredientName,
        String amount,
        String unit,
        LocalDate expirationDate
) {
    public static LeftoverInformation from(Leftover l) {
        return new LeftoverInformation(
                l.getId(),
                l.getUserId(),
                l.getIngredient().name(),
                l.getIngredient().measurement().amount().toPlainString(),
                l.getIngredient().measurement().unit(),
                l.getExpirationDate()
        );
    }
}
