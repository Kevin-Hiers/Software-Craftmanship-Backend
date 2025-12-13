package be.ucll.chappl.leftovers.controllers;

import be.ucll.chappl.leftovers.application.LeftoverCommandHandler;
import be.ucll.chappl.leftovers.application.LeftoverInformation;
import be.ucll.chappl.leftovers.application.LeftoverQueryHandler;
import be.ucll.chappl.leftovers.commands.AddLeftoverCommand;
import be.ucll.chappl.leftovers.commands.RemoveLeftoverCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leftovers")
public class LeftoverController {

    private final LeftoverCommandHandler commands;
    private final LeftoverQueryHandler queries;

    public LeftoverController(LeftoverCommandHandler commands, LeftoverQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping
    public LeftoverInformation add(@RequestBody AddLeftoverCommand cmd) {
        return commands.handle(cmd);
    }

    @GetMapping("/{leftoverId}")
    public LeftoverInformation get(@PathVariable UUID leftoverId) {
        return queries.getById(leftoverId);
    }

    @GetMapping("/user/{userId}")
    public List<LeftoverInformation> listForUser(@PathVariable UUID userId) {
        return queries.listForUser(userId);
    }

    @DeleteMapping("/{leftoverId}")
    public void remove(@PathVariable UUID leftoverId) {
        commands.handle(new RemoveLeftoverCommand(leftoverId));
    }
}
