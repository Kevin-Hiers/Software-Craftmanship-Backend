package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.infrastructure.GroupRepository;
import be.ucll.chappl.recipes.infrastructure.RecipeRepository;
import be.ucll.chappl.users.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GroupQueryHandler {
    private final GroupRepository groups;
    private final UserRepository users;
    private final RecipeRepository recipes;

    public GroupQueryHandler(GroupRepository groups, UserRepository users, RecipeRepository recipes) {
        this.groups = groups;
        this.users = users;
        this.recipes = recipes;
    }

    public GroupInformation getById(UUID id) {
        var g = groups.findById(id).orElseThrow(() -> new NoSuchElementException("group not found"));
        return GroupMapper.toInformation(g, users, recipes);
    }
}
