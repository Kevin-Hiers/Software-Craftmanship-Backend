package be.ucll.chappl.leftovers.infrastructure;

import be.ucll.chappl.leftovers.domain.Leftover;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryLeftoverRepository implements LeftoverRepository {

    private final Map<UUID, Leftover> store = new ConcurrentHashMap<>();

    @Override
    public void save(Leftover leftover) {
        store.put(leftover.getId(), leftover);
    }

    @Override
    public Optional<Leftover> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Leftover> findByUserId(UUID userId) {
        return store.values().stream()
                .filter(l -> l.getUserId().equals(userId))
                .sorted(Comparator.comparing(Leftover::getExpirationDate))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        store.remove(id);
    }
}
