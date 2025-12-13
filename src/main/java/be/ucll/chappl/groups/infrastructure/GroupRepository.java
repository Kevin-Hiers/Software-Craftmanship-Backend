package be.ucll.chappl.groups.infrastructure;

import be.ucll.chappl.groups.domain.Group;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository {
    void save(Group group);
    Optional<Group> findById(UUID id);
}
