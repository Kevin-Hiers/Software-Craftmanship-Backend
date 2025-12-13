package be.ucll.chappl.recipes.infrastructure;

import be.ucll.chappl.recipes.domain.Recipe;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryRecipeRepository implements RecipeRepository {
    private final Map<UUID, Recipe> store = new ConcurrentHashMap<>();
    @Override public void save(Recipe recipe) { store.put(recipe.getId(), recipe); }
    @Override public Optional<Recipe> findById(UUID id) { return Optional.ofNullable(store.get(id)); }
}
