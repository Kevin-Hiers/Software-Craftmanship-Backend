package be.ucll.chappl.users.infrastructure;

import be.ucll.chappl.users.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UUID id);
}
