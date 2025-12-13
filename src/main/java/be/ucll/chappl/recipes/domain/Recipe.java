package be.ucll.chappl.recipes.domain;

import java.util.*;

public class Recipe {
    private final UUID id;
    private final String name;
    private final boolean isPublic;
    private final List<Step> steps;

    public Recipe(UUID id, String name, boolean isPublic, List<Step> steps) {
        if (id == null) throw new IllegalArgumentException("id required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.id = id;
        this.name = name.trim();
        this.isPublic = isPublic;
        this.steps = new ArrayList<>(steps == null ? List.of() : steps);
    }

    public static Recipe create(String name, boolean isPublic, List<Step> steps) {
        return new Recipe(UUID.randomUUID(), name, isPublic, steps);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public boolean isPublic() { return isPublic; }
    public List<Step> getSteps() { return List.copyOf(steps); }
}
