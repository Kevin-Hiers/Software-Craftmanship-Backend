package be.ucll.chappl.groups.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
    private final UUID id;
    private String name;
    private boolean isPublic;
    private final List<Step> steps;

    public Recipe(UUID id, String name, boolean isPublic, List<Step> steps) {
        this.id = Objects.requireNonNull(id, "id is required");
        setName(name);
        this.isPublic = isPublic;
        this.steps = new ArrayList<>(Objects.requireNonNullElse(steps, List.of()));
    }

    public static Recipe create(String name, boolean isPublic, List<Step> steps) {
        return new Recipe(UUID.randomUUID(), name, isPublic, steps);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public boolean isPublic() { return isPublic; }
    public List<Step> getSteps() { return List.copyOf(steps); }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("recipe name is required");
        this.name = name.trim();
    }

    public void setPublic(boolean value) {
        this.isPublic = value;
    }

    public void addStep(Step step) {
        steps.add(Objects.requireNonNull(step, "step is required"));
    }
}
