package be.ucll.chappl.groups.infrastructure;

import be.ucll.chappl.groups.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository {
    void save(Group group);
    Optional<Group> findById(UUID id);
}
