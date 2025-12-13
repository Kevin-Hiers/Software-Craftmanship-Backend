package be.ucll.chappl.users.controllers;

import be.ucll.chappl.users.application.*;
import be.ucll.chappl.users.commands.RegisterUserCommand;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserCommandHandler commands;
    private final UserQueryHandler queries;

    public UserController(UserCommandHandler commands, UserQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping
    public UserInformation register(@RequestBody RegisterUserCommand cmd) {
        return commands.handle(cmd);
    }

    @GetMapping("/{id}")
    public UserInformation get(@PathVariable UUID id) {
        return queries.getById(id);
    }
}
