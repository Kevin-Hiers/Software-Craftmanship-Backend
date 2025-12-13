package be.ucll.chappl.groups.application;

import be.ucll.chappl.groups.domain.Group;

import java.util.List;
import java.util.UUID;

public record GroupInformation(UUID id, List<UserInformation> users, List<ProposalInformation> proposals) {
    public static GroupInformation from(Group g) {
        return new GroupInformation(
                g.getId(),
                g.getUsers().stream().map(UserInformation::from).toList(),
                g.getProposals().stream().map(ProposalInformation::from).toList()
        );
    }
}
