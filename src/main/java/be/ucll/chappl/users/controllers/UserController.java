package be.ucll.chappl.users.controllers;

import be.ucll.chappl.users.application.*;
import be.ucll.chappl.users.commands.LoginUserCommand;
import be.ucll.chappl.users.commands.RegisterUserCommand;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserCommandHandler commands;
    private final UserQueryHandler queries;
    private final UserExistenceQueryHandler existenceQueries;
    private final UserLoginHandler loginHandler;

    public UserController(
            UserCommandHandler commands,
            UserQueryHandler queries,
            UserExistenceQueryHandler existenceQueries,
            UserLoginHandler loginHandler
    ) {
        this.commands = commands;
        this.queries = queries;
        this.existenceQueries = existenceQueries;
        this.loginHandler = loginHandler;
    }

    @PostMapping
    public UserInformation register(@RequestBody RegisterUserCommand cmd) {
        return commands.handle(cmd);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginUserCommand cmd) {
        return loginHandler.handle(cmd);
    }

    @GetMapping("/{id}")
    public UserInformation get(@PathVariable UUID id) {
        return queries.getById(id);
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam String email) {
        return existenceQueries.existsByEmail(email);
    }
}
