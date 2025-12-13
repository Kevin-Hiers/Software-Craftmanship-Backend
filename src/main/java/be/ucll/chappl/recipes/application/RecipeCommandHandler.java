package be.ucll.chappl.recipes.application;

import be.ucll.chappl.recipes.commands.CreateRecipeCommand;
import be.ucll.chappl.recipes.domain.*;
import be.ucll.chappl.recipes.infrastructure.RecipeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RecipeCommandHandler {
    private final RecipeRepository repo;

    public RecipeCommandHandler(RecipeRepository repo) {
        this.repo = repo;
    }

    public RecipeInformation handle(CreateRecipeCommand cmd) {
        var steps = cmd.steps().stream().map(s -> {
            var m = new Measurement(new BigDecimal(s.amount()), s.unit());
            var ing = new Ingredient(s.ingredientName(), m);
            return new Step(ing, s.instruction());
        }).toList();

        var recipe = Recipe.create(cmd.name(), cmd.isPublic(), steps);
        repo.save(recipe);
        return RecipeInformation.from(recipe);
    }
}
