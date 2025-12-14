package be.ucll.chappl.unit.users.application;

import be.ucll.chappl.users.application.UserCommandHandler;
import be.ucll.chappl.users.commands.RegisterUserCommand;
import be.ucll.chappl.users.infrastructure.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCommandHandlerTest {

    @Test
    void givenValidRegisterCommand_whenHandled_thenUserIsCreatedAndStored() {
        var repo = new InMemoryUserRepository();
        var handler = new UserCommandHandler(repo);

        var cmd = new RegisterUserCommand("stefanie", "stefanie@ucll.be", "pw");
        var created = handler.handle(cmd);

        assertNotNull(created.id());
        var loaded = repo.findById(created.id()).orElseThrow();
        assertEquals("stefanie", loaded.getUsername());
        assertEquals("stefanie@ucll.be", loaded.getEmail());
    }
}
