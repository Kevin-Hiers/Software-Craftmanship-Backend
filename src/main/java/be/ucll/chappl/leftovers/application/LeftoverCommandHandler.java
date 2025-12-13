package be.ucll.chappl.leftovers.application;

import be.ucll.chappl.leftovers.commands.AddLeftoverCommand;
import be.ucll.chappl.leftovers.commands.RemoveLeftoverCommand;
import be.ucll.chappl.leftovers.domain.Leftover;
import be.ucll.chappl.leftovers.infrastructure.LeftoverRepository;
import be.ucll.chappl.recipes.domain.Ingredient;
import be.ucll.chappl.recipes.domain.Measurement;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class LeftoverCommandHandler {

    private final LeftoverRepository leftovers;
    private final UserRepository users;

    public LeftoverCommandHandler(LeftoverRepository leftovers, UserRepository users) {
        this.leftovers = leftovers;
        this.users = users;
    }

    public LeftoverInformation handle(AddLeftoverCommand cmd) {
        users.findById(cmd.userId()).orElseThrow(() -> new NoSuchElementException("user not found"));

        var measurement = new Measurement(new BigDecimal(cmd.amount()), cmd.unit());
        var ingredient = new Ingredient(cmd.ingredientName(), measurement);
        var expiration = LocalDate.parse(cmd.expirationDate());

        var leftover = Leftover.create(cmd.userId(), ingredient, expiration);
        leftovers.save(leftover);

        return LeftoverInformation.from(leftover);
    }

    public void handle(RemoveLeftoverCommand cmd) {
        leftovers.findById(cmd.leftoverId()).orElseThrow(() -> new NoSuchElementException("leftover not found"));
        leftovers.deleteById(cmd.leftoverId());
    }
}
