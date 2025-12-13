package be.ucll.chappl.recipes.application;

import be.ucll.chappl.recipes.domain.Recipe;

import java.util.UUID;

public record RecipeInformation(UUID id, String name, boolean isPublic) {
    public static RecipeInformation from(Recipe r) {
        return new RecipeInformation(r.getId(), r.getName(), r.isPublic());
    }
}
