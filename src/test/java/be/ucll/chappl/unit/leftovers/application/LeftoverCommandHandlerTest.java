package be.ucll.chappl.unit.leftovers.application;

import be.ucll.chappl.leftovers.application.LeftoverCommandHandler;
import be.ucll.chappl.leftovers.application.LeftoverQueryHandler;
import be.ucll.chappl.leftovers.commands.AddLeftoverCommand;
import be.ucll.chappl.leftovers.infrastructure.InMemoryLeftoverRepository;
import be.ucll.chappl.users.application.UserCommandHandler;
import be.ucll.chappl.users.commands.RegisterUserCommand;
import be.ucll.chappl.users.infrastructure.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LeftoverCommandHandlerTest {

    @Test
    void givenNonExistingUser_whenAddingLeftover_thenThrows() {
        var usersRepo = new InMemoryUserRepository();
        var leftoversRepo = new InMemoryLeftoverRepository();
        var handler = new LeftoverCommandHandler(leftoversRepo, usersRepo);

        var cmd = new AddLeftoverCommand(
                java.util.UUID.randomUUID(),
                "Rice",
                "200",
                "g",
                "2026-01-01"
        );

        assertThrows(NoSuchElementException.class, () -> handler.handle(cmd));
    }

    @Test
    void givenExistingUserAndMultipleLeftovers_whenListingForUser_thenResultsAreSortedByExpiration() {
        var usersRepo = new InMemoryUserRepository();
        var leftoversRepo = new InMemoryLeftoverRepository();

        var userHandler = new UserCommandHandler(usersRepo);
        var user = userHandler.handle(new RegisterUserCommand("stefanie", "stefanie@ucll.be", "pw"));

        var commands = new LeftoverCommandHandler(leftoversRepo, usersRepo);
        var queries = new LeftoverQueryHandler(leftoversRepo, usersRepo);

        commands.handle(new AddLeftoverCommand(user.id(), "Milk", "1", "l", "2026-02-01"));
        commands.handle(new AddLeftoverCommand(user.id(), "Bread", "2", "pc", "2026-01-10"));

        var list = queries.listForUser(user.id());

        assertEquals(2, list.size());
        assertEquals("Bread", list.get(0).ingredientName());
        assertEquals("Milk", list.get(1).ingredientName());
    }
}