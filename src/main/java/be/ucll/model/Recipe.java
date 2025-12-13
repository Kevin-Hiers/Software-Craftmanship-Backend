package be.ucll.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "IsPublic must be set")
    private Boolean isPublic;

    @ElementCollection
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    @Valid
    private List<Step> steps = new ArrayList<>();

    public Recipe() {}

    public Recipe(String name, boolean isPublic, List<Step> steps) {
        this.name = name;
        this.isPublic = isPublic;
        if (steps != null) this.steps = steps;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getIsPublic() { return isPublic; }
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }

    public List<Step> getSteps() { return steps; }
    public void setSteps(List<Step> steps) {
        this.steps = (steps == null) ? new ArrayList<>() : steps;
    }
}
