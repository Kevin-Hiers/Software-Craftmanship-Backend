package be.ucll.chappl.groups.infrastructure;

import be.ucll.chappl.groups.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGroupRepository implements GroupRepository {
    private final Map<UUID, Group> store = new ConcurrentHashMap<>();

    @Override public void save(Group group) { store.put(group.getId(), group); }
    @Override public Optional<Group> findById(UUID id) { return Optional.ofNullable(store.get(id)); }
}
