package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.domain.Group;
import be.ucll.chappl.recipes.infrastructure.RecipeRepository;
import be.ucll.chappl.users.infrastructure.UserRepository;

import java.util.List;

final class GroupMapper {
    private GroupMapper() {}

    static GroupInformation toInformation(Group g, UserRepository users, RecipeRepository recipes) {
        List<UserInformation> userInfos = g.getUserIds().stream()
                .map(id -> users.findById(id).orElseThrow(() -> new IllegalStateException("group references missing user " + id)))
                .map(u -> new UserInformation(u.getId(), u.getUsername(), u.getEmail()))
                .toList();

        List<ProposalInformation> proposalInfos = g.getProposals().stream()
                .map(p -> {
                    var r = recipes.findById(p.recipeId())
                            .orElseThrow(() -> new IllegalStateException("proposal references missing recipe " + p.recipeId()));
                    return new ProposalInformation(p.id(), p.recipeId(), r.getName(), r.isPublic(), p.votes());
                })
                .toList();

        return new GroupInformation(g.getId(), userInfos, proposalInfos);
    }
}
