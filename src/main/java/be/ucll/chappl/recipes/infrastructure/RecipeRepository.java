package be.ucll.chappl.recipes.infrastructure;

import be.ucll.chappl.recipes.domain.Recipe;

import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository {
    void save(Recipe recipe);
    Optional<Recipe> findById(UUID id);
}
