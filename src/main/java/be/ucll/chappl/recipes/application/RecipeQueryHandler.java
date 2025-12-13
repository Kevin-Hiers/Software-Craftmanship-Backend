package be.ucll.chappl.recipes.application;

import be.ucll.chappl.recipes.infrastructure.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RecipeQueryHandler {
    private final RecipeRepository repo;

    public RecipeQueryHandler(RecipeRepository repo) {
        this.repo = repo;
    }

    public RecipeInformation getById(UUID id) {
        return repo.findById(id).map(RecipeInformation::from)
                .orElseThrow(() -> new NoSuchElementException("recipe not found"));
    }
}
