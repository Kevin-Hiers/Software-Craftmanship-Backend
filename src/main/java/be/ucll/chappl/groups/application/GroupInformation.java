package be.ucll.chappl.groups.application;

import java.util.List;
import java.util.UUID;

public record GroupInformation(UUID id, List<UserInformation> users, List<ProposalInformation> proposals) {}
