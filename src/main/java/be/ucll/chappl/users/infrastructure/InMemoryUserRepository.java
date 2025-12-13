package be.ucll.chappl.users.infrastructure;

import be.ucll.chappl.users.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<UUID, User> store = new ConcurrentHashMap<>();

    @Override public void save(User user) { store.put(user.getId(), user); }
    @Override public Optional<User> findById(UUID id) { return Optional.ofNullable(store.get(id)); }
}
