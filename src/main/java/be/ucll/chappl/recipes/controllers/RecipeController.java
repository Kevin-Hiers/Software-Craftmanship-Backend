package be.ucll.chappl.recipes.controllers;

import be.ucll.chappl.recipes.application.*;
import be.ucll.chappl.recipes.commands.CreateRecipeCommand;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeCommandHandler commands;
    private final RecipeQueryHandler queries;

    public RecipeController(RecipeCommandHandler commands, RecipeQueryHandler queries) {
        this.commands = commands;
        this.queries = queries;
    }

    @PostMapping
    public RecipeInformation create(@RequestBody CreateRecipeCommand cmd) {
        return commands.handle(cmd);
    }

    @GetMapping("/{id}")
    public RecipeInformation get(@PathVariable UUID id) {
        return queries.getById(id);
    }
}
